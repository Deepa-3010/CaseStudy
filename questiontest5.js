const sinon = require('sinon')

const questionExport = require("../question")
const questionHelper = require('../questionHelper');

let questionHelperStub;

describe('Test question flow 5', function () {
   it('Entering wrong input', async function () {
    questionHelperStub = sinon.stub(questionHelper, "askQuestion")
    questionHelperStub.onCall(0).resolves(1);
    questionHelperStub.onCall(1).resolves(2);
    questionHelperStub.onCall(2).resolves(-1);
    questionHelperStub.onCall(3).resolves(1);
    questionHelperStub.onCall(4).resolves(2);
    questionHelperStub.onCall(5).resolves(1);
    questionHelperStub.onCall(6).resolves(1);

    await questionExport.questioning();
    questionHelperStub.restore();

  })
}
)