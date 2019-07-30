const question = require('../question')
const questionHelper = require('../questionHelper')
const sinon = require('sinon')
let questionHelperStub;
var answerArray=[1,1,2,9,1,1,2,13]//,1,0,-1,-1,2,12,1,-1,2,12,0,-1,1,2,1,-1,1,1,1,-1,2,12,2,1,2,13,2,1,1,2,2,10,2,12,2,2,2,12,1,-1];
describe('Test question flow 1', function() {
  before(function() {
    questionHelperStub = sinon.stub(questionHelper,'askQuestion')
  })
  for(var i=0;i<2;i+=4)
    {
  it('select based on touch devices', async function() {
    // console.log("For i=",i);
    questionHelperStub.onCall(0).resolves(answerArray[i])
    // if(answerArray[i+1]!=-1)
      questionHelperStub.onCall(1).resolves(answerArray[i+1])
    // if(answerArray[i+2]!=-1)
      questionHelperStub.onCall(2).resolves(answerArray[i+2])
    // if(answerArray[i+3]!=-1)
      questionHelperStub.onCall(3).resolves(answerArray[i+3])
    await question.questioning();

  })
}
  // it("slect touch devices", async function() {
  //   questionHelperStub.onCall(0).resolves(1)
  //   questionHelperStub.onCall(1).resolves(2)
  //   questionHelperStub.onCall(2).resolves(1)
  //   await question.questioning();
  // })
});

// describe('Test question flow 2', function() {
//   before(function() {
//     questionHelperStub = sinon.stub(questionHelper,'askQuestion')
//   })
//   it('select based on touch devices', async function() {
//     questionHelperStub.resolves(1)
//     await question.questioning();
//     it("slect touch devices",async function(){
//       questionHelperStub.resolves(1);
//       await question.touchSelected();
//       it('display only on touch',async function(){
//         questionHelperStub.resolves(1);
//         await question.touch();
//       })
//     })
//   });
// });