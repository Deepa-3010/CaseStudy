const sinon = require('sinon')
//var expect = chai.expect;

const questionExport = require("../question")
const questionHelper = require('../questionHelper');

let questionHelperStub;

describe('Test question flow 1', function () {

  // before(function () {
  //   questionHelperStub = sinon.stub(questionHelper, "askQuestion")
  // })

  var answerArray = [1, 1, 2, 9, 1, 1, 2, 13];

var i = 0;
while (i < 8) {

  console.log("Before it For i : ", i)

  it('Filter by touch devices', async function () {
    console.log("For i : ", i);
    questionHelperStub = sinon.stub(questionHelper, "askQuestion")

    questionHelperStub.onCall(0).resolves(answerArray[i]);
    questionHelperStub.onCall(1).resolves(answerArray[i + 1]);
    questionHelperStub.onCall(2).resolves(answerArray[i + 2]);
    questionHelperStub.onCall(3).resolves(answerArray[i + 3]);

    await questionExport.questioning();
    questionHelperStub.restore();

  })
  
  console.log("Init i : ", i)
  i = i + 4;
  console.log("Updates i : ", i)
}

  // it('Filter by touch and exit',async function () {
  //   questionHelperStub = sinon.stub(questionHelper, "askQuestion")
  //   questionHelperStub.onCall(0).resolves(1);
  //   questionHelperStub.onCall(1).resolves(1);
  //   questionHelperStub.onCall(2).resolves(2);
  //   questionHelperStub.onCall(3).resolves(13);
  //   await questionExport.questioning();

  // })

})
























