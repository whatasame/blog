import {parentPort, workerData} from "worker_threads";

const multiply = (op1, op2) => op1 * op2;

parentPort.postMessage(multiply(workerData.op1, workerData.op2));
