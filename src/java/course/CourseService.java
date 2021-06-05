/**
 *
 * @author Dinh Kong Thanh
 */
package course;

import common.entities.Course;

public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService() {
        this.courseRepository = new CourseRepository();
    }

    public Course getCourse(int id) {
        try {
            Course result = courseRepository.getCourse(id);

            if (result == null) {
                // redirect to 404
                System.out.println("Something wrong");
                return null;
            }

            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
