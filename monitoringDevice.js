function MonitoringDevice(name,id,touch,screensize)
{
    this._name=name;
    this._id=id;
    this._touch=touch;
    this._screensize=screensize;
    MonitoringDevice.prototype.getIfTouch=function(){
        return this._touch;
    }
    MonitoringDevice.prototype.getScreenSize=function(){
        return this._screensize;
    }
    MonitoringDevice.prototype.getName=function(){
        return this._name;
    }
    MonitoringDevice.prototype.getId=function(){
        return this._id;
    }

}
var names=["GoldmanWay30E","GoldmanWay40E","Efficia10","Efficia12","Efficia100","Efficia120","Efficia150","IntelliVue430","IntelliVue450","IntelliVue500","IntelliVue550","IntelliVue600","IntelliVue700","IntelliVue800"];
var touch=[false,false,false,false,true,true,true,true,true,true,true,true,true,true];
var screensize=[10,12,10,12,10,12,15,9,10,12,12,15,15,14];
var arrayOfDevices=[];
(function()
{
   for(var i=0;i<14;i++)
   {
    arrayOfDevices.push(new MonitoringDevice(names[i],i+1,touch[i],screensize[i]));
   }
})(); 

async function returnDevices(touch,inches)
{  
    var flag=0;
    for(var i=0;i<14;i++)
    {
        if(arrayOfDevices[i].getIfTouch()==touch && arrayOfDevices[i].getScreenSize()==inches)
           {
            console.log(arrayOfDevices[i].getName());
           flag=1;
           }
    }
    if(flag==0)
    console.log("No such monitoring device found");
nextCall();
}
async function returnOnlyByTouch(touch)
{
    var flag=0;
    for(var i=0;i<14;i++)
    {
        if(arrayOfDevices[i].getIfTouch()==touch)
        {
            flag=1;
            console.log(arrayOfDevices[i].getName());
        }
    }
    if(flag==0)
    console.log("No monitoring device found");
    nextCall();
}

async function resultOnlyBySize(size)
{
    var flag=0;
    for(var i=0;i<14;i++)
    {
        if(arrayOfDevices[i].getScreenSize()==size)
        {
            flag=1;
            console.log(arrayOfDevices[i].getName());
        }
    }
    if(flag==0)
    console.log("No monitoring Device found");
    nextCall();
}
async function nextCall()
{
    let questionHelperExport=require('./questionHelper');
    let questionExport=require('./question');
    var answer=await questionHelperExport.askQuestion("\n Press 1:to continue\n Press 2:to exit\n");
    if(parseInt(answer)==1)
    {
        questionExport.questioning();
    }
    else
    {
       //let moduleExport=require('./modules');
       questionExport.exitMessage();
    }
}
module.exports={result:returnDevices,resultTouch:returnOnlyByTouch,resultSize:resultOnlyBySize};
