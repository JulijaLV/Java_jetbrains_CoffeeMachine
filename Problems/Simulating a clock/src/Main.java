class Clock {

   public int hours = 12;
   public int minutes = 0;

   public void next() {
       if (minutes == 59 ) {
           if (hours == 12) {
               hours = 1;
           } else {
               hours++;
           }
           minutes = 0;
       } else {
           minutes++;
       }
    }
}