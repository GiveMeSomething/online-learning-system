/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package slider;

import common.entities.Slider;
import common.entities.Status;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

public class SliderService {

    private final SliderRepository sliderRepository;

    public SliderService() {
        this.sliderRepository = new SliderRepository();
    }

    public List<Slider> getSlidersList(String keyword, Status status) {
        try {
            List<Slider> result = sliderRepository.getSlidersList(keyword, status);

            if (result == null || result.size() == 0) {
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

    public List<Slider> displaySliders() {
        try {
            List<Slider> result = sliderRepository.displaySliders();

            if (result == null || result.size() == 0) {
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

    public boolean deleteSliderById(int sliderId) {
        try {
            return sliderRepository.deleteSliderById(sliderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean addNewSlider(Slider slider, InputStream inputStream) {
        try {
            return sliderRepository.addNewSlider(slider, inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
