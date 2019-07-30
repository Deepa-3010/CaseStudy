const readline = require('readline');
const r1=readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

module.exports = {
  async askQuestion( questions ) {
    return new Promise(( res ) => {
      r1.question(questions, answer => {
        res(answer);
      })
    })
  }
}