const ERROR = 1;
const WARN = 2;
const INFO = 3;
const DEBUG = 4;
const TYPE = INFO;

const logger = {
    error(thread, msg) {
        this.log(thread, ERROR, 'ERROR', msg, 'color:red;');
    },
    warn(thread, msg) {
        this.log(thread, WARN, 'WARN', msg, 'color:yellow;');
    },
    info(thread, msg) {
        this.log(thread, INFO, 'INFO', msg,'color:black;');
    },
    debug(thread, msg) {
        this.log(thread, DEBUG, 'DEBUG', msg, 'color:gray;');
    },
    log(thread, type, typeStr, msg, style) {
        if (TYPE >= type) {
          console.group(thread);
            console.log('%c'+ typeStr + ' ' + this.getDate(), style);
            if (msg)
                console.log(msg);
          console.groupEnd();
        }
    },
    getDate() {
        return this.getNowData();
    },
    getNowData() {
        const date = new Date();
        return date.getFullYear() + '-' +
            (date.getMonth() + 1) + '-' +
            date.getDate() + ' ' +
            date.getHours() + ':' +
            date.getMinutes() + ':' +
            date.getSeconds();
    }
};

// export default logger
