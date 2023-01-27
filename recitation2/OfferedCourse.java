public class OfferedCourse extends Course {
   // TODO: Declare private fields
   private String instructorName;
   private String location;
   private String classTime;
   
   // TODO: Define mutator methods -
   //      setInstructorName(), setLocation(), setClassTime()
   
   public void setInstructorName(String instructorName) {
      this.instructorName = instructorName;
   }
   public void setLocation(String location) {
      this.location = location;
   }
   public void setClassTime(String classTime) {
      this.classTime = classTime;
   }

   // TODO: Define accessor methods -
   //      getInstructorName(), getLocation(), getClassTime()
   public String getInstructorName() {
      return instructorName;
   }
   public String getLocation() {
      return location;
   }
   public String getClassTime() {
      return classTime;
   }
}
