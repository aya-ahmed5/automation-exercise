package demoplaze.drivers;

public enum Createdriver {
    edge{

        public driverFactory driverValue(){
            return  new EdgeFactory();
        }
    },
    chrome{

        public  driverFactory driverValue(){
         return  new chromeFactory();
        }

    };

    public abstract driverFactory driverValue();
}
