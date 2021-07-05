/**
 * Jun 26, 2021
 *
 * @author Hoang Tien Minh
 */
package slider;

import common.entities.Slider;

public class SliderService {

    private final SliderRepository sliderRepository;

    public SliderService() {
        this.sliderRepository = new SliderRepository();
    }
    
    public Slider getSliderDetail(int sliderId) {
        try {
            return sliderRepository.getSliderDetail(sliderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateSliderDetail(String image, String title, int status_id, String notes, int sliderId) {
        try {
           return sliderRepository.updateSliderDetail(image, title, status_id, notes, sliderId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
