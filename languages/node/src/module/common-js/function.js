const {odd, even} = require('./variable');

const checkOddOrEven = (num) => {
  if (num % 2) {
    return odd;
  }
  return even;
}

module.exports = checkOddOrEven;
