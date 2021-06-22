/**
 * Jun 13, 2021
 *
 * @author Hoang Tien Minh
 */
package price_package;

import common.entities.PricePack;
import java.util.List;

public class PricePackageService {

    private final PricePackageRepository packageRepository;

    public PricePackageService() {
        packageRepository = new PricePackageRepository();
    }

    public List<PricePack> getPricePackage(int index) {
        try {
            return packageRepository.pagingPricePackage(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int countTotalPricePackage() {
        try {
            return packageRepository.countTotalPricePackage();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean addPackage(int duration, String name, double price, int status, String descriptions, double discount) {
        try {
            return packageRepository.addPackage(duration, name, price, status, descriptions, discount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletePackage(int id) {
        try {
            return packageRepository.deletePackage(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean editPackage(int id, int duration, String name, double price, int status, String descriptions, double discount) {
        try {
            return packageRepository.editPackage(id, duration, name, price, status, descriptions, discount);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        PricePackageService dao = new PricePackageService();
        System.out.println(dao.countTotalPricePackage());
    }
}
