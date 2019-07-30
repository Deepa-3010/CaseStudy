const sinon = require('sinon')
//var expect = chai.expect;
const controllerExport=require('../controller')
const mainExport=require("../main")
const greetingExport=require("../greeting")
let questionExport=require("../question")
const questionHelper = require('../questionHelper');
let displayAgentExport=require("../displayDevices");
const monitoringDeviceExport=require('../monitoringDevice')
describe('to check it controller is executed',function(){
    it('to check',function(){
        controllerExport.controller();
     } )
     it('check if main called',function(){
         mainExport.mainObject(global);
     })
     it('check if greeting is called',function(){
          greetingExport.greetFun();
     })
     it('check if questioing is called',function(){
        questionExport.questioning();
   })
it('check if displayAgent is called for both touch and size',function(){
    displayAgentExport.display(global,true,10);
})
it('check if monitoring is called for both touch and size',function(){
    monitoringDeviceExport.result(global,true,10);
})
it('check if displayAgent is called for tuch',function(){
    displayAgentExport.displayTouch(global,true);
})
it('check if displayAgent is called for size',function(){
    displayAgentExport.displaySize(global,10);
})
it('check if monitoring is called for touch',function(){
    monitoringDeviceExport.resultTouch(global,true);
})
it('check if monitoring is called for size',function(){
    monitoringDeviceExport.resultSize(global,10);
})
})