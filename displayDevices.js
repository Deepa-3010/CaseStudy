let monitoringDeviceExport=require("./monitoringDevice");
function displayAgent(moduleRef,touch,size){
    var _touch=touch;
    var _size=size;
    moduleRef.displayDevicesFun=(function(){
        monitoringDeviceExport.result(_touch,_size);
    })();
}
function displayAgentOnlyTouch(moduleRef,touch){
    var _touch=touch;
    moduleRef.displayDevicesFun=(function(){
        monitoringDeviceExport.resultTouch(_touch);
    })();
}
function displayAgentOnlySize(moduleRef,size){
    var _size=size;
    moduleRef.displayDevicesFun=(function(){
        monitoringDeviceExport.resultSize(_size);
    })();
}
module.exports={display:displayAgent,displayTouch:displayAgentOnlyTouch,displaySize:displayAgentOnlySize};
