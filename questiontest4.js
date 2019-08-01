const sinon = require('sinon')

const questionExport = require("../question")
const questionHelper = require('../questionHelper');

let questionHelperStub;

describe('Test question flow 4', function () {
   it('checking if the correct size is given', async function () {
    questionHelperStub = sinon.stub(questionHelper, "askQuestion")
    questionHelperStub.onCall(0).resolves(2);
    questionHelperStub.onCall(1).resolves(3);
    questionHelperStub.onCall(2).resolves(5);
    questionHelperStub.onCall(3).resolves(9);
    questionHelperStub.onCall(4).resolves(2);
    questionHelperStub.onCall(4).resolves(1);
    questionHelperStub.onCall(4).resolves(2);
    await questionExport.questioning();
    questionHelperStub.restore();

  })
}
)