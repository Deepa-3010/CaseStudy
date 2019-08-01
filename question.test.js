const sinon = require('sinon')

const questionExport = require("../question")
const questionHelper = require('../questionHelper');

let questionHelperStub;

describe('Test question flow 1', function () {
   it('Filter by touch devices', async function () {
    questionHelperStub = sinon.stub(questionHelper, "askQuestion")
    questionHelperStub.onCall(0).resolves(1);
    questionHelperStub.onCall(1).resolves(1);
    questionHelperStub.onCall(2).resolves(2);
    questionHelperStub.onCall(3).resolves(9);

    await questionExport.questioning();
    questionHelperStub.restore();

  })


})
























