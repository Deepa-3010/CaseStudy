let questionExport=require("./question");
    //questionExport.beginQuestion();
    const assert=require("./mocha").assert;

describe("to give output",function(){
    it("for touch only",function(){
        let questionCopy=sinon.mock(questionExport.askQuestion());
        //let questions=sinon.mock(questionExport.questioning())
        questionCopy.WithArgs(1);

    })
}

)