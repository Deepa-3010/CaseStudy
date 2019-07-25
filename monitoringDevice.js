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

var m1=new MonitoringDevice("GoldmanWay30E",1,false,10);
var m2=new MonitoringDevice("GoldmanWay40E",2,false,12);
var m3=new MonitoringDevice("Efficia10",3,false,10);
var m4=new MonitoringDevice("Efficia12",4,false,12);
var m5=new MonitoringDevice("Efficia100",5,true,10);
var m6=new MonitoringDevice("Efficia120",6,true,12);
var m7=new MonitoringDevice("Efficia150",7,true,15);
var m8=new MonitoringDevice("IntelliVue430",8,true,9);
var m9=new MonitoringDevice("IntelliVue450",9,true,10);
var m10=new MonitoringDevice("IntelliVue500",10,true,12);
var m11=new MonitoringDevice("IntelliVue550",11,true,12);
var m12=new MonitoringDevice("IntelliVue600",12,true,15);
var m13=new MonitoringDevice("IntelliVue700",13,true,15);
var m14=new MonitoringDevice("IntelliVue800",14,true,14);
//console.log(m1.getName()+ m1.getIfTouch() + m1.getScreenSize());
//console.log(m3.getName()+ m3.getIfTouch() + m3.getScreenSize());
//console.log(m10.getName()+ m10.getIfTouch() + m10.getScreenSize());

var arrayOfDevices=[];
arrayOfDevices.push(m1);
arrayOfDevices.push(m2);
arrayOfDevices.push(m3);
arrayOfDevices.push(m4);
arrayOfDevices.push(m5);
arrayOfDevices.push(m6);
arrayOfDevices.push(m7);
arrayOfDevices.push(m8);
arrayOfDevices.push(m9);
arrayOfDevices.push(m10);
arrayOfDevices.push(m11);
arrayOfDevices.push(m12)
arrayOfDevices.push(m13);
arrayOfDevices.push(m14);

function returnDevices(touch,inches)
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
}
module.exports={result:returnDevices};
