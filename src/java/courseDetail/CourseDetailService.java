/**
 *
 * @author Dinh Kong Thanh
 */

package courseDetail;

import entities.Course;


public class CourseDetailService {
 private final CourseDetailRepository courseDetailRepository;

    public CourseDetailService() {
        this.courseDetailRepository = new CourseDetailRepository();
    }

    public Course getCourse(int id) {
        try {
            Course result = courseDetailRepository.getCourse(id);

            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }

            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
    
    
     
}
