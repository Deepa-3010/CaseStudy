const question = require('../question')
const questionHelper = require('../questionHelper')
const sinon = require('sinon')
let questionHelperStub;
describe('Test question flow 1', function() {
  before(function() {
    questionHelperStub = sinon.stub(questionHelper,'askQuestion')
  })
  it('direct exit', async function() {
    questionHelperStub.resolves(0)
    await question.questioning();
  });
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