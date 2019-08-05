(function () {
 
    let mainExport=require("./main");
    mainExport.mainObject(global);
    let greetingExport=require("./greeting");
    greetingExport.greetFun();
    let questionExport=require("./question");
    questionExport.questioning();
    
})();