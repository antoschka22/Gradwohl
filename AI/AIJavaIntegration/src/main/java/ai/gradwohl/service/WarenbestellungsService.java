package at.gradwohl.website.service;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import at.gradwohl.website.repository.warenbestellung.WarenbestellungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class WarenbestellungsService {

    private static final int TEIGIG_MIN_ID = 2000;
    private static final int TEIGIG_MAX_ID = 3000;

    private final WarenbestellungRepository warenbestellungRepository;

    @Autowired
    public WarenbestellungsService(WarenbestellungRepository warenbestellungRepository) {
        this.warenbestellungRepository = warenbestellungRepository;
    }

    public List<Map<String, Object>> getLast30DaysOrdersByFiliale(Filiale filiale) {
        LocalDate thirtyDaysAgo = LocalDate.now().minusDays(30);
        List<Warenbestellung> orders = warenbestellungRepository.findAllById_Filiale(filiale)
                .stream()
                .filter(order -> order.getId().getDatum().isAfter(thirtyDaysAgo))
                .collect(Collectors.toList());

        return orders.stream().map(order -> {
            Map<String, Object> dataMap = new HashMap<>();
            dataMap.put("product_id", order.getId().getProdukt());
            dataMap.put("date", order.getId().getDatum());
            // TODO Recheck the .getId part, this seems unreliable
            dataMap.put("teigig", isTeigig(order.getId().getProdukt().getId()) ? order.getMenge() : 0);
            dataMap.put("frisch", isTeigig(order.getId().getProdukt().getId()) ? 0 : order.getMenge());
            return dataMap;
        }).collect(Collectors.toList());
    }

    public void deleteOrder(WarenbestellungId id) {
        warenbestellungRepository.deleteById(id);
    }

    public void deleteOrdersByFiliale(Filiale filiale) {
        warenbestellungRepository.deleteByFiliale(filiale);
    }

    public Warenbestellung saveOrUpdateOrder(Warenbestellung warenbestellung) {
        return warenbestellungRepository.save(warenbestellung);
    }

    private boolean isTeigig(int productId) {
        return productId >= TEIGIG_MIN_ID && productId <= TEIGIG_MAX_ID;
    }

    // Additional service methods can be implemented as needed...
}
