const sinon = require('sinon')

const questionExport = require("../question")
const questionHelper = require('../questionHelper');

let questionHelperStub;

describe('Test question flow 2', function () {
   it('Exit at any point', async function () {
    questionHelperStub = sinon.stub(questionHelper, "askQuestion")
    questionHelperStub.onCall(0).resolves(1);
    questionHelperStub.onCall(1).resolves(0)

    await questionExport.questioning();
    questionHelperStub.restore();

  })
}
)