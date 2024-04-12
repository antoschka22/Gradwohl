package at.gradwohl.website.service.warenbestellung;

import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import at.gradwohl.website.repository.lieferbar.LieferbarRepository;
import at.gradwohl.website.repository.produkt.ProduktRepository;
import at.gradwohl.website.repository.warenbestellung.WarenbestellungRepository;
import java.util.Random;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WarenbestellungService {
    private final WarenbestellungRepository warenbestellungRepository;
    private final LieferbarRepository lieferbarRepository;
    private final FilialeRepository filialeRepository;
    private final ProduktRepository produktRepository;

    public List<Warenbestellung> getAllWarenbestellungen() {
        return warenbestellungRepository.findAll();
    }

    public List<Warenbestellung> getWarenbestellungenByDate(LocalDate datum) {
        return warenbestellungRepository.findAllById_Datum(datum);
    }

    public List<Warenbestellung> getGenerateWarenbestellungen(LocalDate datum, int filiale){
        Random random = new Random();

        Optional<Filiale> optFiliale = filialeRepository.findById(filiale);

        if(optFiliale.isEmpty())
            throw new IllegalArgumentException("Filiale doesnt exist");


        List<Lieferbar> lieferbar = lieferbarRepository.findAllById_Firma(optFiliale.get().getFirma());
        List<Warenbestellung> warenbestellungs = new ArrayList<>();

        for(Lieferbar lief : lieferbar){
            boolean hbFound = false;
            Optional<Produkt> proHB = Optional.ofNullable(produktRepository.findById(lief.getId().getProdukt().getId() + 2000));
            WarenbestellungId idHB = null;
            if(proHB.isPresent()){
                hbFound = true;
                idHB =
                        WarenbestellungId.builder()
                                .datum(datum)
                                .filiale(optFiliale.get())
                                .produkt(proHB.get())
                                .build();
            }


            boolean feier = lief.isSamstag() || lief.isSonntag();

            WarenbestellungId id =
                    WarenbestellungId.builder()
                            .datum(datum)
                            .filiale(optFiliale.get())
                            .produkt(lief.getId().getProdukt())
                            .build();

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "VK Brote")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(1) + 5;
                else
                    menge = random.nextInt(1) + 3;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                        .id(id)
                                        .menge(menge)
                                        .build();

                if(hbFound){
                    Warenbestellung wareHB = 
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "VK Kleingebäck")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(2) + 10;
                else
                    menge = random.nextInt(2) + 8;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "VK Brot Stangen")){
                int randomInt = random.nextInt(2);

                double menge = randomInt / 2.0;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "VK Mehlspeisen")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(2) + 10;
                else
                    menge = random.nextInt(2) + 8;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "VK ostern")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(2) + 18;
                else
                    menge = random.nextInt(2) + 13;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "VK Jour Gebäck")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(3) + 5;
                else
                    menge = random.nextInt(2) + 5;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "Ostersachen")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(2) + 18;
                else
                    menge = random.nextInt(2) + 13;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "Muttertagsachen")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(2) + 18;
                else
                    menge = random.nextInt(2) + 12;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "Weihnachtensachen")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(2) + 18;
                else
                    menge = random.nextInt(2) + 12;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }

            if(Objects.equals(lief.getId().getProdukt().getProduktgruppe().getName(), "Snack")){
                double menge = 0;
                if(!feier)
                    menge = random.nextInt(3) + 2;
                else
                    menge = random.nextInt(1) + 2;

                Warenbestellung ware =
                        Warenbestellung.builder()
                                .id(id)
                                .menge(menge)
                                .build();

                if(hbFound){
                    Warenbestellung wareHB =
                            Warenbestellung.builder()
                                    .id(idHB)
                                    .menge(menge)
                                    .build();
                    warenbestellungs.add(wareHB);
                }

                warenbestellungs.add(ware);
            }
        }

        warenbestellungRepository.saveAll(warenbestellungs);

        return warenbestellungs;
    }

    @Transactional
    public List<Warenbestellung> addWarenbestellungen(List<Warenbestellung> warenbestellungen) {
        List<Warenbestellung> bestellungenVorhanden = getAllWarenbestellungen();
        for(Warenbestellung w : warenbestellungen){
            if(bestellungenVorhanden.contains(w))
                throw new IllegalArgumentException("Already exists");

        }
        return warenbestellungRepository.saveAll(warenbestellungen);
    }

    @Transactional
    public Warenbestellung updateWarenbestellung(WarenbestellungId id, Warenbestellung updatedWarenbestellung) {
        Optional<Warenbestellung> existingProdukt = warenbestellungRepository.findById(id);
        if(existingProdukt.isPresent()){
            Warenbestellung wareToUpdate =
                    Warenbestellung.builder()
                            .menge(updatedWarenbestellung.getMenge())
                            .id(updatedWarenbestellung.getId())
                            .build();
            warenbestellungRepository.deleteById(id);
            return warenbestellungRepository.save(wareToUpdate);
        } else {
            throw new IllegalArgumentException("Warenbestellung not found");
        }
    }

    @Transactional
    public void deleteWarenbestellung(WarenbestellungId id) {
        Optional<Warenbestellung> existingProdukt = warenbestellungRepository.findById(id);
        if(existingProdukt.isPresent())
            warenbestellungRepository.deleteById(id);
        else
            throw new IllegalArgumentException("Warenbestellung doesnt exist");
    }


    public List<Warenbestellung> getWarenbestellungenByFiliale(Filiale filiale) {
        return warenbestellungRepository.findAllById_Filiale(filiale);
    }
}
