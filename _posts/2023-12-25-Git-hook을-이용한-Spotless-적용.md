---
title: |
    Git hook을 이용한 Spotless 적용
date: 2023-12-25T19:26:00.000Z
categories: [Automation, Git, Gradle]
tags: [Formatting]
---


팀 프로젝트를 진행할 때 사소하지만 가장 신경쓰이는 것은 코드 포매팅이라고 생각합니다. 각자 사용하는 IDE가 다를 수도 있고, 같은 IDE를 사용하더라도 개인마다 설정이 달라 통일하기란 여간 쉬운일이 아니죠. 통일되지 않은 코드 형식은 PR에 불필요한 변경 사항을 남겨 코드 리뷰를 어렵게 만듭니다.


이번 글에서는 Spotless를 적용하여 통일된 코드 형식 환경을 구축하고 Git hook으로 commit 할 때마다 자동으로 포매팅 되도록 설정해보겠습니다. 


참고한 자료는 아래와 같습니다.

- [Apply Spotless formatting with Git pre-commit hook - Michael Messell](https://medium.com/@mmessell/apply-spotless-formatting-with-git-pre-commit-hook-1c484ea68c34)

## Spotless 적용하기


Spotless는 여러 언어에 대한 포매팅을 지원하는 프로그램입니다. 주로 JVM 언어 계열에서 사용하며 Gradle이나 Maven을 이용해 쉽게 사용하고 IDE와의 통합까지 가능합니다.


> 이번 글에서는 Gradle와 Java을 기준으로 설명합니다. 다른 환경의 경우 [공식 레포지터리](https://github.com/diffplug/spotless)를 참고해주세요.


### 플러그인 추가


우선 `build.gradle`에 Spotless 플러그인을 추가합니다. 최신 버전은 [이 곳](https://plugins.gradle.org/plugin/com.diffplug.spotless)에서 확인할 수 있습니다.


```groovy
plugins {
  id "com.diffplug.spotless" version "6.25.0" // latest version
}
```


### Spotless 설정 추가


다음으로 `build.gradle`에 Spotless 설정을 추가합니다.


```groovy
spotless {
    java {
        importOrder()                       // (1)
        removeUnusedImports()               // (2)
        cleanthat()                         // (3)
        googleJavaFormat()                  // (4)
        formatAnnotations()                 // (5)
        licenseHeader '/* (C) $YEAR */'     // (6)
    }
}
```


각 설정 별 기능은 다음과 같습니다.

1. `importOrder()`: import 순서를 변경합니다.
2. `removeUnusedImports()`: 사용하지 않는 import를 제거합니다.
3. `cleanthat()`: [Cleanthat](https://github.com/solven-eu/cleanthat)을 적용합니다.
4. `googleJavaFormat()`: Google Java convention을 이용하여 코드를 포매팅합니다.
5. `formatAnnotations()`: 애너테이션을 포매팅합니다.
6. `licenseHeader`: 코드 상단에 라이센스 정보를 추가합니다.

위 설정은 가장 기본적인 설정이며 설정마다 추가로 커스텀 할 수 있는 [옵션들](https://github.com/diffplug/spotless/tree/main/plugin-gradle#java)이 존재합니다.


### Spotless task 실행


마지막으로 Gradle을 다시 로드하면 Spotless와 관련된 task가 추가됩니다. 주요 task는 다음과 같습니다.

- spotlessApply: 코드 포매팅 진행
- spotlessCheck: 잘못된 코드 확인

## Commit 할 때 자동으로 적용하기


Spotless를 사용하는 가장 큰 목적은 코드 포매팅입니다. 그러나 코드를 작성할 때마다 포매팅을 위해 task를 실행하는 것은 번거롭습니다. 따라서, Git hook을 이용하여 커밋할 때마다 자동으로 task를 실행하도록 구성해보겠습니다.


우선, 다음과 같은 쉘 스크립트 파일을 `pre-commit`이란 이름으로 저장합니다. 제 경우 `scripts/pre-commit`에 저장하였습니다.


```bash
#!/bin/sh

stagedFiles=$(git diff --staged --name-only)

./gradlew spotlessApply

for file in $stagedFiles; do
  if test -f "$file"; then
    git add $file
  fi
done
```


이후 해당 쉘 스크립트를 실행할 수 있도록 실행 권한을 부여합니다.


```bash
chmod +x scripts/pre-commit # Your scripts path
```


마지막으로 Git hook 폴더에 해당 스크립트를 복사하여 commit 시 Git이 스크립트를 실행하도록 합니다.


```bash
cp scripts/pre-commit .git/hooks/pre-commit
```


이제 Git을 이용하여 commit 할 때마다 Spotless task를 수행하게 됩니다.


## 새로운 환경에서 Git hook 쉽게 적용하기


`.git` 디렉토리는 원격 저장소에 저장되지 않으므로 환경마다 Git hook 설정을 해야합니다. 위에서 했던 방법처럼 수동으로 구성할 수 있지만 Gradle task를 이용하여 자동화할 수 있습니다.


### Git hook 구성 task 추가


Git hook 구성을 처리하는 task를 build.gradle에 추가합니다.


```bash
task updateGitHooks(type: Copy) {
    from './scripts/pre-commit'
    into './.git/hooks'
}
compileJava.dependsOn(updateGitHooks) // Run updateGitHooks after compileJava task
```


Gradle을 다시 로드 후 `updateGitHooks`를 실행하면 Git hook 설정이 완료됩니다.


마지막 줄에 있는 `compileJava.dependsOn(updateGitHooks)`는 `compileJava` 실행 시 `updateGitHooks`를 자동으로 실행하는 코드입니다. 프로젝트를 실행하면 `compileJava`가 실행되므로 Git hook 구성을 깜빡하고 잊더라도 자동으로 구성하도록 설정하였습니다.


## 마무리하며


이번 글에서는 Gradle과 Java 환경에서 Spotless과 Git hook을 이용하여 코드 포매팅을 자동화하였습니다. 팀 프로젝트를 하면서 포매팅으로 귀찮았던 적이 있었다면 가볍게 사용해보시고 취향에 맞게 설정하여 원하는 환경을 구축할 수 있길 바랍니다!

