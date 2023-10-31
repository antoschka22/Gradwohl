package at.gradwohl.website.service;

import at.gradwohl.website.model.dienstplan.Dienstplan;
import at.gradwohl.website.model.dienstplan.DienstplanId;
import at.gradwohl.website.model.filiale.Filiale;
import at.gradwohl.website.model.firma.Firma;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaub;
import at.gradwohl.website.model.firmenUrlaub.FirmenUrlaubId;
import at.gradwohl.website.model.kundenbestellung.Kundenbestellung;
import at.gradwohl.website.model.kundenbestellung.KundenbestellungId;
import at.gradwohl.website.model.lieferbar.Lieferbar;
import at.gradwohl.website.model.lieferbar.LieferbarId;
import at.gradwohl.website.model.mitarbeiter.Mitarbeiter;
import at.gradwohl.website.model.mitarbeiterrole.MitarbeiterRole;
import at.gradwohl.website.model.nachricht.Nachricht;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSenden;
import at.gradwohl.website.model.nachrichtSenden.NachrichtSendenId;
import at.gradwohl.website.model.produkt.Mehl;
import at.gradwohl.website.model.produkt.Produkt;
import at.gradwohl.website.model.produktgruppe.Produktgruppe;
import at.gradwohl.website.model.urlaubstage.Urlaubstage;
import at.gradwohl.website.model.vorlage.Vorlage;
import at.gradwohl.website.model.vorlage.VorlageId;
import at.gradwohl.website.model.warenbestellung.Warenbestellung;
import at.gradwohl.website.model.warenbestellung.WarenbestellungId;
import at.gradwohl.website.repository.dienstplan.DienstplanRepository;
import at.gradwohl.website.repository.filiale.FilialeRepository;
import at.gradwohl.website.repository.firma.FirmaRepository;
import at.gradwohl.website.repository.firmenUrlaub.FirmenUrlaubRepository;
import at.gradwohl.website.repository.kundenbestellung.KundenbestellungRepository;
import at.gradwohl.website.repository.lieferbar.LieferbarRepository;
import at.gradwohl.website.repository.mitarbeiter.MitarbeiterRepository;
import at.gradwohl.website.repository.nachricht.NachrichtRepository;
import at.gradwohl.website.repository.nachrichtsenden.NachrichtSendenRepository;
import at.gradwohl.website.repository.produkt.ProduktRepository;
import at.gradwohl.website.repository.produktgruppe.ProduktgruppeRepository;
import at.gradwohl.website.repository.role.RoleRepository;
import at.gradwohl.website.repository.urlaubstage.UrlaubgstageRepository;
import at.gradwohl.website.repository.vorlage.VorlageRepository;
import at.gradwohl.website.repository.warenbestellung.WarenbestellungRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class initApplication {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(DienstplanRepository dienstplanRepo,
                                        RoleRepository roleRepo,
                                        FilialeRepository filialeRepository,
                                        MitarbeiterRepository mitarbeiterRepository,
                                        FirmaRepository firmaRepository,
                                        ProduktRepository produktRepository,
                                        ProduktgruppeRepository produktgruppeRepository,
                                        VorlageRepository vorlageRepository,
                                        KundenbestellungRepository kundenbestellungRepository,
                                        WarenbestellungRepository warenbestellungRepository,
                                        LieferbarRepository lieferbarRepository,
                                        NachrichtRepository nachrichtRepository,
                                        NachrichtSendenRepository nachrichtSendenRepository,
                                        UrlaubgstageRepository urlaubgstageRepository,
                                        FirmenUrlaubRepository firmenUrlaubRepository) {
        return args -> {
            Firma wien =
                    Firma.builder()
                            .name("Wien")
                            .build();
            Firma burgenland=
                    Firma.builder()
                            .name("Burgenland")
                            .build();

            Firma teigwerkstatt =
                    Firma.builder()
                            .name("Olivers Teigwerkstätte")
                            .build();

            List<Firma> firmen = Arrays.asList(
                wien, burgenland, teigwerkstatt
            );

            firmaRepository.saveAll(firmen);


            Filiale fleischmarkt =
                    Filiale.builder()
                            .name("Fleischmarkt")
                            .firma(wien)
                            .build();

            Filiale naglergasse =
                    Filiale.builder()
                            .name("Naglergasse")
                            .firma(wien)
                            .build();
            Filiale landstrasse =
                    Filiale.builder()
                            .name("Landstraße")
                            .firma(wien)
                            .build();

            Filiale mariahilfer =
                    Filiale.builder()
                            .name("Mariahilferstraße")
                            .firma(wien)
                            .build();

            Filiale ziegler =
                    Filiale.builder()
                            .name("Zieglergasse")
                            .firma(wien)
                            .build();

            Filiale josefstadt =
                    Filiale.builder()
                            .name("Josefstadt")
                            .firma(wien)
                            .build();

            Filiale hietzing =
                    Filiale.builder()
                            .name("Hietzing")
                            .firma(wien)
                            //.SOoffen(true)
                            .build();

            Filiale doebling =
                    Filiale.builder()
                            .name("Döbling")
                            .firma(wien)
                            .build();

            Filiale schwechat =
                    Filiale.builder()
                            .name("Schwechat")
                            .firma(wien)
                            .build();

            Filiale moedling =
                    Filiale.builder()
                            .name("Mödling")
                            .firma(wien)
                            .build();

            Filiale baden =
                    Filiale.builder()
                            .name("Baden")
                            .firma(wien)
                            .build();

            Filiale neustadt =
                    Filiale.builder()
                            .name("Wiener Neustadt")
                            .firma(wien)
                            .build();

            Filiale eisenstadt =
                    Filiale.builder()
                            .name("Eisenstadt")
                            .firma(burgenland)
                            .build();

            Filiale deutschkreutz =
                    Filiale.builder()
                            .name("Deutschkreutz")
                            .SOoffen(true)
                            .firma(burgenland)
                            .build();

            Filiale weppersdorf =
                    Filiale.builder()
                            .name("Weppersdorf")
                            .SOoffen(true)
                            .firma(burgenland)
                            .build();

            Filiale oberpullendorf =
                    Filiale.builder()
                            .name("Oberpullendorf")
                            .SOoffen(true)
                            .firma(burgenland)
                            .build();

            Filiale alserStrasse =
                    Filiale.builder()
                            .name("Alser Straße")
                            .firma(teigwerkstatt)
                            .build();

            Filiale goldschmied =
                    Filiale.builder()
                            .name("Goldschmiedgasse")
                            .firma(teigwerkstatt)
                            .build();

            Filiale krugerstrasse =
                    Filiale.builder()
                            .name("Krugerstrasse")
                            .firma(teigwerkstatt)
                            .build();

            Filiale plankengasse =
                    Filiale.builder()
                            .name("Plankengasse")
                            .firma(teigwerkstatt)
                            .build();

            List<Filiale> filialen = Arrays.asList(
                plankengasse, krugerstrasse, goldschmied, alserStrasse, oberpullendorf, weppersdorf, deutschkreutz, eisenstadt, neustadt, baden, moedling, schwechat, doebling, hietzing, josefstadt, mariahilfer, ziegler, landstrasse, naglergasse, fleischmarkt
            );

            filialeRepository.saveAll(filialen);

            MitarbeiterRole zentrale =
                    MitarbeiterRole.builder()
                            .role("Zentrale")
                            .build();

            MitarbeiterRole verkauf =
                    MitarbeiterRole.builder()
                            .role("Verkauf")
                            .build();

            MitarbeiterRole leiter =
                    MitarbeiterRole.builder()
                            .role("Leiter")
                            .build();


            List<MitarbeiterRole> rolen = Arrays.asList(
                    zentrale, verkauf, leiter
            );

            roleRepo.saveAll(rolen);

            Mitarbeiter barbara =
                    Mitarbeiter.builder()
                            .role(leiter)
                            .name("Barbara")
                            .password(passwordEncoder.encode("admin"))
                            .filiale(hietzing)
                            .build();

            Mitarbeiter szimone =
                    Mitarbeiter.builder()
                            .role(verkauf)
                            .name("Szimone")
                            .password(passwordEncoder.encode("admin"))
                            .filiale(hietzing)
                            .build();

            Mitarbeiter christa =
                    Mitarbeiter.builder()
                            .role(leiter)
                            .name("Christa")
                            .password(passwordEncoder.encode("admin"))
                            .filiale(ziegler)
                            .build();

            Mitarbeiter andrea =
                    Mitarbeiter.builder()
                            .role(verkauf)
                            .name("Andrea")
                            .password(passwordEncoder.encode("admin"))
                            .springer(true)
                            .build();

            Mitarbeiter maybrit =
                    Mitarbeiter.builder()
                            .role(verkauf)
                            .name("Maybrit")
                            .password(passwordEncoder.encode("admin"))
                            .filiale(hietzing)
                            .build();

            Mitarbeiter nicole =
                    Mitarbeiter.builder()
                            .role(zentrale)
                            .name("Nicole")
                            .password(passwordEncoder.encode("admin"))
                            .filiale(weppersdorf)
                            .build();
            List<Mitarbeiter> mitarbeiter = Arrays.asList(
                    barbara, szimone, nicole, christa, andrea, maybrit
            );

            mitarbeiterRepository.saveAll(mitarbeiter);


            Produktgruppe brote=
                    Produktgruppe.builder()
                            .name("VK Brote")
                            .build();

            Produktgruppe gebaeck=
                    Produktgruppe.builder()
                            .name("VK Kleingebäck")
                            .build();

            Produktgruppe jourGebaeck=
                    Produktgruppe.builder()
                            .name("VK Jour Gebäck")
                            .build();

            Produktgruppe mehlspeisen=
                    Produktgruppe.builder()
                            .name("VK Mehlspeisen")
                            .build();

            Produktgruppe kekse=
                    Produktgruppe.builder()
                            .name("VK Kekse")
                            .build();

            Produktgruppe kuchen=
                    Produktgruppe.builder()
                            .name("VK Kuchen")
                            .build();

            Produktgruppe torten=
                    Produktgruppe.builder()
                            .name("VK ostern")
                            .build();

            Produktgruppe ostern =
                    Produktgruppe.builder()
                            .name("Ostersachen")
                            .build();

            Produktgruppe weihnachten =
                    Produktgruppe.builder()
                            .name("Weihnachtensachen")
                            .build();

            Produktgruppe muttertag =
                    Produktgruppe.builder()
                            .name("Muttertagsachen")
                            .build();

            Produktgruppe sontiges=
                    Produktgruppe.builder()
                            .name("VK Sonstiges")
                            .build();

            Produktgruppe organisatorisch=
                    Produktgruppe.builder()
                            .name("organisatorisch")
                            .build();

            Produktgruppe stange =
                    Produktgruppe.builder()
                            .name("VK Brot Stangen")
                            .build();

            Produktgruppe snack =
                    Produktgruppe.builder()
                            .name("Snack")
                            .build();


            List<Produktgruppe> produktgruppen = Arrays.asList(
                    ostern, weihnachten, muttertag, snack, brote, gebaeck, jourGebaeck, mehlspeisen, kuchen, torten, sontiges, organisatorisch, kekse, stange
            );

            produktgruppeRepository.saveAll(produktgruppen);


            Produkt roggenbrot =
                    Produkt.builder()
                            .id(60)
                            .name("Roggenbrot")
                            .bio(true)
                            .mehl(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();


            Produkt bauernbrot =
                    Produkt.builder()
                            .id(66)
                            .name("Bauernbrot")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt bauernbrotHB =
                    Produkt.builder()
                            .id(2066)
                            .name("Bauernbrot HB")
                            .bio(true)
                            .mehl(Mehl.Roggen)
                            .mehlMischung(Mehl.Roggen)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt dinkelbrotOhneHefe =
                    Produkt.builder()
                            .id(906)
                            .name("Dinkelbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrotOhneHefeHB =
                    Produkt.builder()
                            .id(2906)
                            .name("Dinkelbrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt pharaobrotOhneHefe =
                    Produkt.builder()
                            .id(68)
                            .name("Pharaobrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrot =
                    Produkt.builder()
                            .id(71)
                            .name("Dinkelbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrotHB =
                    Produkt.builder()
                            .id(2071)
                            .name("Dinkelbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkeltoast =
                    Produkt.builder()
                            .id(72)
                            .name("Dinkeltoast")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt leinsamenAmaranthbrotOhneHefe =
                    Produkt.builder()
                            .id(196)
                            .name("Leinsamen-Amaranthbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt leinsamenAmaranthbrotOhneHefeHB =
                    Produkt.builder()
                            .id(2196)
                            .name("Leinsamen-Amaranthbrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Roggen)
                            .mehlMischung(Mehl.Roggen)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt backfermentbrotOhneHefe =
                    Produkt.builder()
                            .id(227)
                            .name("Backfermentbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt karottenNussbrot =
                    Produkt.builder()
                            .id(288)
                            .name("Karotten-Nussbrot")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt karottenNussHB =
                    Produkt.builder()
                            .id(2288)
                            .name("Karotten-Nussbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt kraftbrotOhneHefe =
                    Produkt.builder()
                            .id(492)
                            .name("Kraftbrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt kraftbrotOhneHefeHB =
                    Produkt.builder()
                            .id(2492)
                            .name("Kraftbrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt chiabrotOhneHefe =
                    Produkt.builder()
                            .id(117)
                            .name("Chiabrot ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt chiabrotOhneHefeHB =
                    Produkt.builder()
                            .id(2117)
                            .name("Chiabrot ohne Hefe HB")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt dinkelbrotStange =
                    Produkt.builder()
                            .id(449)
                            .name("Dinkelbrot Stange")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(false)
                            .produktgruppe(stange)
                            .build();

            Produkt siebenkornStange =
                    Produkt.builder()
                            .id(206)
                            .name("Siebenkorn Stange")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(stange)
                            .build();

            Produkt vkBioKuerbiskernbrotStange =
                    Produkt.builder()
                            .id(410)
                            .name("VK Bio KürbiskernbrotStange")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(stange)
                            .build();

            Produkt bauernbrotStange =
                    Produkt.builder()
                            .id(450)
                            .name("Bauernbrot Stange")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(Mehl.Roggen)
                            .hb(false)
                            .produktgruppe(stange)
                            .build();

            Produkt kuerbiskernbrot =
                    Produkt.builder()
                            .id(821)
                            .name("Kürbiskernbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt kuerbisbrotHB =
                    Produkt.builder()
                            .id(2821)
                            .name("Kürbiskernbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt suesskartoffelbrot =
                    Produkt.builder()
                            .id(831)
                            .name("Süßkartoffelbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt suesskartoffelbrotHB =
                    Produkt.builder()
                            .id(2831)
                            .name("Süßkartoffelbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt olivenbrot =
                    Produkt.builder()
                            .id(498)
                            .name("Olivenbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt olivenbrotHB =
                    Produkt.builder()
                            .id(2498)
                            .name("Olivenbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt roteRuebenbrot =
                    Produkt.builder()
                            .id(408)
                            .name("Rote Rübenbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt roteRuebenbrotHB =
                    Produkt.builder()
                            .id(2408)
                            .name("Rote Rübenbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt zwiebelbrot =
                    Produkt.builder()
                            .id(409)
                            .name("Zwiebelbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt zwiebelbrotHB =
                    Produkt.builder()
                            .id(2409)
                            .name("Zwiebelbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt nussbrotstange =
                    Produkt.builder()
                            .id(344)
                            .name("Nussbrotstange")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();


            Produkt nussbrotleibRund =
                    Produkt.builder()
                            .id(74)
                            .name("Nussbrotleib Rund")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt nussbrotlaibRundHB =
                    Produkt.builder()
                            .id(2074)
                            .name("Nussbrotleib Rund HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt baerlauchbrot =
                    Produkt.builder()
                            .id(754)
                            .name("Bärlauchbrot")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt baerlauchbrotHB =
                    Produkt.builder()
                            .id(2754)
                            .name("Bärlauchbrot HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();

            Produkt sandwich =
                    Produkt.builder()
                            .id(268)
                            .name("Sandwich")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt baguette =
                    Produkt.builder()
                            .id(247)
                            .name("Baguette")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(brote)
                            .build();

            Produkt baguetteHB =
                    Produkt.builder()
                            .id(2247)
                            .name("Baguette HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .hb(true)
                            .produktgruppe(brote)
                            .build();


            Produkt kaesestangerl =
                    Produkt.builder()
                            .id(129)
                            .name("Käsestangerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kaesestangerlHB =
                    Produkt.builder()
                            .id(2129)
                            .name("Käsestangerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt vitalwecker =
                    Produkt.builder()
                            .id(458)
                            .name("Vitalweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt vitalweckerHB =
                    Produkt.builder()
                            .id(2458)
                            .name("Vitalwecker (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt olinebweckerl =
                    Produkt.builder()
                            .id(2498)
                            .name("Olivenweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt olivenweckerlHB =
                    Produkt.builder()
                            .id(2498)
                            .name("Olivenweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelsemmel =
                    Produkt.builder()
                            .id(119)
                            .name("Dinkelsemmel")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelsemmelHB =
                    Produkt.builder()
                            .id(2119)
                            .name("Dinkelsemmel (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornsitz =
                    Produkt.builder()
                            .id(113)
                            .name("Dinkelkornspitz")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornsitzHB =
                    Produkt.builder()
                            .id(2113)
                            .name("Dinkelkornspitz (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornspitzSalz =
                    Produkt.builder()
                            .id(285)
                            .name("Dinkelkornspitz Salz")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerl =
                    Produkt.builder()
                            .id(124)
                            .name("Dinkelweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerlHB =
                    Produkt.builder()
                            .id(2124)
                            .name("Dinkelweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();


            Produkt dinkelkornspitzOhneBestreu =
                    Produkt.builder()
                            .id(444)
                            .name("Dinkelkornspitz ohne Betreung")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelkornspitzOhneBestreuHB =
                    Produkt.builder()
                            .id(2444)
                            .name("Dinkelkornspitz ohne Betreu (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoweckerl =
                    Produkt.builder()
                            .id(417)
                            .name("Pharaoweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoweckerlHB =
                    Produkt.builder()
                            .id(2417)
                            .name("Pharaoweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();


            Produkt chiaweckerl =
                    Produkt.builder()
                            .id(140)
                            .name("Chiaweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt chiaweckerlHB =
                    Produkt.builder()
                            .id(2140)
                            .name("Chiaweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkellaugenstangerl =
                    Produkt.builder()
                            .id(123)
                            .name("Dinkellaugenstangerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkellaugenstangerlHB =
                    Produkt.builder()
                            .id(2123)
                            .name("Dinkellaugenstangerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt hirseweckerl =
                    Produkt.builder()
                            .id(152)
                            .name("Hirseweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt hirseweckerlHB =
                    Produkt.builder()
                            .id(2152)
                            .name("Hirseweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt salzstangerl =
                    Produkt.builder()
                            .id(118)
                            .name("Salzstangerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt salzbrezerlKlein =
                    Produkt.builder()
                            .id(156)
                            .name("Salzbrezerl Klein")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoamaranthweckerOhnHefe =
                    Produkt.builder()
                            .id(209)
                            .name("PharaoAmarantweckerl ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt pharaoamaranthweckerOhnHefeHB =
                    Produkt.builder()
                            .id(2209)
                            .name("PharaoAmarantweckerl ohne Hefe (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kornweckerlOhneHefe =
                    Produkt.builder()
                            .id(419)
                            .name("Kornweckerl ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kornweckerlOhneHefeHB =
                    Produkt.builder()
                            .id(2419)
                            .name("Kornweckerl ohne Hefe (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerlOhneHefe =
                    Produkt.builder()
                            .id(418)
                            .name("Dinkelweckerl ohne Hefe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt dinkelweckerlOhneHefeHB =
                    Produkt.builder()
                            .id(2418)
                            .name("Dinkelweckerl ohne Hefe (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt foccachia =
                    Produkt.builder()
                            .id(549)
                            .name("Foccachia")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt foccachiaHB =
                    Produkt.builder()
                            .id(2549)
                            .name("Foccachia (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuerbislaibchen =
                    Produkt.builder()
                            .id(154)
                            .name("Kürbislaibchen")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuerbislaibchenHB =
                    Produkt.builder()
                            .id(2154)
                            .name("Kürbislaibchen (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt basilikumweckerl =
                    Produkt.builder()
                            .id(750)
                            .name("Basilikumweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt basilikumweckerlHB =
                    Produkt.builder()
                            .id(2750)
                            .name("Basilikumweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuemmelweckerl =
                    Produkt.builder()
                            .id(750)
                            .name("Kümmelweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kuemmelweckerlHB =
                    Produkt.builder()
                            .id(2750)
                            .name("Kümmelweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt baerlauchweckerl =
                    Produkt.builder()
                            .id(205)
                            .name("Bärlauchweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt baerlauchweckerlHB =
                    Produkt.builder()
                            .id(2205)
                            .name("Bärlauchweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kurkumaweckerl =
                    Produkt.builder()
                            .id(743)
                            .name("Kurkumaweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt kurkumaweckerlHB =
                    Produkt.builder()
                            .id(2743)
                            .name("Kurkumaweckerl (5)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(gebaeck)
                            .build();

            Produkt rosinenweckerl =
                    Produkt.builder()
                            .id(453)
                            .name("Rosinenweckerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt rosinenweckerlHB =
                    Produkt.builder()
                            .id(2453)
                            .name("Rosinenweckerl (5)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussMandelweckerl =
                    Produkt.builder()
                            .id(116)
                            .name("NussMandelweckerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussMandelweckerlHB =
                    Produkt.builder()
                            .id(2116)
                            .name("NussMandelweckerl (5)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt studentenfutter =
                    Produkt.builder()
                            .id(368)
                            .name("Studentenfutter")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt studentenfutterHB =
                    Produkt.builder()
                            .id(2368)
                            .name("Studentenfutter (5)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt dinkelSchnecke =
                    Produkt.builder()
                            .id(324)
                            .name("D-Schnecke")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt dinkelSchneckeHB =
                    Produkt.builder()
                            .id(2324)
                            .name("D-Schnecke (3)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaobriochestriezerl =
                    Produkt.builder()
                            .id(110)
                            .name("Pharaobriochestriezerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaobriochestriezerlHB =
                    Produkt.builder()
                            .id(2110)
                            .name("Pharaobriochestriezerl (3)")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfengolatsche =
                    Produkt.builder()
                            .id(125)
                            .name("Topfengolatsche")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfengolatscheHB =
                    Produkt.builder()
                            .id(2125)
                            .name("Topfengolatsche teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt powidlgolatsche =
                    Produkt.builder()
                            .id(126)
                            .name("Powidlgolatsche")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt powidlgolatscheHB =
                    Produkt.builder()
                            .id(2126)
                            .name("Powidlgolatsche teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nusskipferl =
                    Produkt.builder()
                            .id(132)
                            .name("Nusskipferl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nusskipferlHB =
                    Produkt.builder()
                            .id(2132)
                            .name("Nusskipferl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt mohnkipfer =
                    Produkt.builder()
                            .id(133)
                            .name("Mohnkipferl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt mohnkipferHB =
                    Produkt.builder()
                            .id(2133)
                            .name("Mohnkipfer teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfenmarille =
                    Produkt.builder()
                            .id(134)
                            .name("MohnkipfeTopfenmarille")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt topfenmarilleHB =
                    Produkt.builder()
                            .id(2134)
                            .name("MohnkipfeTopfenmarille teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt dinkelcroissant =
                    Produkt.builder()
                            .id(241)
                            .name("Dinkelcroissant")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt dinkelcroissantHB =
                    Produkt.builder()
                            .id(2241)
                            .name("Dinkelcroissant teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt Schokostanger =
                    Produkt.builder()
                            .id(415)
                            .name("Schokostangerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt schokostangerHB =
                    Produkt.builder()
                            .id(2415)
                            .name("Schokostangerl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaocroissant =
                    Produkt.builder()
                            .id(363)
                            .name("Pharaocroissant ")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt pharaocroissantHB =
                    Produkt.builder()
                            .id(2363)
                            .name("Pharaocroissant teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt ribiselstangerl =
                    Produkt.builder()
                            .id(406)
                            .name("Ribiselstangerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt ribiselstangerlHB =
                    Produkt.builder()
                            .id(2406)
                            .name("Ribiselstangerl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussino =
                    Produkt.builder()
                            .id(198)
                            .name("Nussino")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt nussinoHB =
                    Produkt.builder()
                            .id(2198)
                            .name("Nussino teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kastanienzipf =
                    Produkt.builder()
                            .id(308)
                            .name("Kastanienzipf")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kastanienzipfHB =
                    Produkt.builder()
                            .id(2308)
                            .name("Kastanienzipf teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kirschpolster =
                    Produkt.builder()
                            .id(127)
                            .name("Kirschpolster")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kirschpolsterHB =
                    Produkt.builder()
                            .id(2127)
                            .name("Kirschpolster teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt apfeltascherl =
                    Produkt.builder()
                            .id(172)
                            .name("Apfeltascherl")
                            .bio(false)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt apfeltascherlHB =
                    Produkt.builder()
                            .id(2172)
                            .name("Apfeltascherl teigig")
                            .bio(false)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt birnentascherl =
                    Produkt.builder()
                            .id(210)
                            .name("Birnentascherl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt birnentascherlHB =
                    Produkt.builder()
                            .id(2210)
                            .name("Birnentascherl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();


            Produkt kuerbiskipfer =
                    Produkt.builder()
                            .id(256)
                            .name("Kürbiskipferl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt kuerbiskipferHB =
                    Produkt.builder()
                            .id(2256)
                            .name("Kürbiskipferl teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marzipankrone =
                    Produkt.builder()
                            .id(131)
                            .name("Marzipankrone")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marzipankroneHB =
                    Produkt.builder()
                            .id(2131)
                            .name("Marzipankrone teigig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt fruechteplunder =
                    Produkt.builder()
                            .id(177)
                            .name("Früchteplunder")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillenKrapfen =
                    Produkt.builder()
                            .id(191)
                            .name("Marillen Krapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt vanillekKapfen =
                    Produkt.builder()
                            .id(173)
                            .name("Vanille Krapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt powidlKrapfen =
                    Produkt.builder()
                            .id(151)
                            .name("Powidl Krapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillenKrapfenVegan =
                    Produkt.builder()
                            .id(494)
                            .name("Marillen Krapfen vegan")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillenKrapfenYour =
                    Produkt.builder()
                            .id(139)
                            .name("Marillen Krapfen your")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt erdbeerkrapfen =
                    Produkt.builder()
                            .id(479)
                            .name("Erdbeerkrapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt apfelsrtudel =
                    Produkt.builder()
                            .id(928)
                            .name("Apfelstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt topfensrtudel =
                    Produkt.builder()
                            .id(929)
                            .name("Topfenstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt marillensrtudel =
                    Produkt.builder()
                            .id(968)
                            .name("Marillenstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt birneMohnstrudel =
                    Produkt.builder()
                            .id(2002)
                            .name("Birne Mohnstrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt obststrudel =
                    Produkt.builder()
                            .id(908)
                            .name("Obststrudel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt egerlaender =
                    Produkt.builder()
                            .id(128)
                            .name("Egerländer")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt pharaoLinzerschnitte =
                    Produkt.builder()
                            .id(269)
                            .name("Pharao Linzerschnitte")
                            .bio(true)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt obstfleck =
                    Produkt.builder()
                            .id(135)
                            .name("Obstfleck")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt birnenStreuselschnitte =
                    Produkt.builder()
                            .id(278)
                            .name("Birnen Streuselschnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt apfelschnitte =
                    Produkt.builder()
                            .id(511)
                            .name("Apfelschnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt mohnZitronenschnitte =
                    Produkt.builder()
                            .id(2001)
                            .name("Mohn Zitronenschnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt karottenroelchen =
                    Produkt.builder()
                            .id(2003)
                            .name("Karottenröllchen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt karottenZucchinischnitte =
                    Produkt.builder()
                            .id(323)
                            .name("Karotten Zucchinischnitte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt nussecke =
                    Produkt.builder()
                            .id(2004)
                            .name("Nussecke")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt linzerstanger =
                    Produkt.builder()
                            .id(538)
                            .name("Linzerstanger")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt linzerauge =
                    Produkt.builder()
                            .id(329)
                            .name("Linzerauge")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt nougartherz =
                    Produkt.builder()
                            .id(425)
                            .name("Nougartherz")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt sacherwuerfelHerzEi =
                    Produkt.builder()
                            .id(950)
                            .name("Sacherwürfel/Herz/Ei")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt schokoberg =
                    Produkt.builder()
                            .id(691)
                            .name("Schockoberg")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(torten)
                            .build();

            Produkt striezelMitMandelspliter =
                    Produkt.builder()
                            .id(309)
                            .name("Striezel mit Mandelspliter")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();

            Produkt striezelOhneBestreuung =
                    Produkt.builder()
                            .id(381)
                            .name("Striezel ohne Betreuung")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(mehlspeisen)
                            .build();




            Produkt jourSemmerl =
                    Produkt.builder()
                            .id(15)
                            .name("Jour Semmerl Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourSemmerlHB =
                    Produkt.builder()
                            .id(3015)
                            .name("Jour Semmerl Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourWachauer =
                    Produkt.builder()
                            .id(23)
                            .name("Jour Wachauer Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourWachauerHB =
                    Produkt.builder()
                            .id(3023)
                            .name("Jour Wachauer Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourlaugenstanger =
                    Produkt.builder()
                            .id(45)
                            .name("Jour Laugenstanger Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourlaugenstangerHB =
                    Produkt.builder()
                            .id(3045)
                            .name("Jour Laugenstanger Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourkaesestangerl =
                    Produkt.builder()
                            .id(70)
                            .name("Jour Käsestanger Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourkaesestangerlHB =
                    Produkt.builder()
                            .id(3070)
                            .name("Jour Käsestanger Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourSalzstangerl =
                    Produkt.builder()
                            .id(73)
                            .name("Jour Salzstangerl Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt grammelpogatscherl =
                    Produkt.builder()
                            .id(162)
                            .name("Grammelpogatscherl Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt grammelpogatscherlHB =
                    Produkt.builder()
                            .id(3162)
                            .name("Grammelpogatscherl Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourMohnlaibchen =
                    Produkt.builder()
                            .id(175)
                            .name("Jour Mohnlaibchen Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourMohnlaibchenHB =
                    Produkt.builder()
                            .id(3175)
                            .name("Jour Mohnlaibchen Stk.HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt joursesamlaibchen =
                    Produkt.builder()
                            .id(176)
                            .name("Jour Sesamlaibchen Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt joursesamlaibchenHB =
                    Produkt.builder()
                            .id(3176)
                            .name("Jour Sesamlaibchen Stk.HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourNussMandelweckerl =
                    Produkt.builder()
                            .id(180)
                            .name("Jour Nuss-Mandelweckerl Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourNussMandelweckerlHB =
                    Produkt.builder()
                            .id(3180)
                            .name("Jour Nuss-Mandelweckerl Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourSalzstangerlBioStk =
                    Produkt.builder()
                            .id(183)
                            .name("Jour Salzstangerl Bio Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourPharaoweckerl =
                    Produkt.builder()
                            .id(187)
                            .name("Jour Pharaoweckerl Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourPharaoweckerlHB =
                    Produkt.builder()
                            .id(3187)
                            .name("Jour Pharaoweckerl Stk.HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt jourKornspitz =
                    Produkt.builder()
                            .id(215)
                            .name("Jour Kornspitz Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt jourKornspitzHB =
                    Produkt.builder()
                            .id(3215)
                            .name("Jour Kornspitz Stk.HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourGrahamweckerl =
                    Produkt.builder()
                            .id(235)
                            .name("Jour Grahamweckerl Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt jourGrahamweckerlHB =
                    Produkt.builder()
                            .id(235)
                            .name("Jour Grahamweckerl Stk.HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourTomMozzTascherlab30Stueck =
                    Produkt.builder()
                            .id(85)
                            .name("Jour Tom/Mozz.tascherl ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt jourTomMozzTascherlab30StueckHB =
                    Produkt.builder()
                            .id(3085)
                            .name("Jour Tom/Mozz.tascherl ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourKuerbiskernweckerlab30Stueck =
                    Produkt.builder()
                            .id(179)
                            .name("Jour Kürbiskernweckerl ab30Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt jourKuerbiskernweckerlab30StueckHB =
                    Produkt.builder()
                            .id(3179)
                            .name("Jour Kürbiskernweckerl ab30Stk.HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt Kaesestangerlab30Stueck =
                    Produkt.builder()
                            .id(186)
                            .name("Jour Käsestangerl ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt Kaesestangerlab30StueckHB =
                    Produkt.builder()
                            .id(3186)
                            .name("Jour Käsestangerl ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt hirseweckerlab30Stueck =
                    Produkt.builder()
                            .id(424)
                            .name("Jour Hirseweckerl ab30Stk.")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt hirseweckerlab30StueckHB =
                    Produkt.builder()
                            .id(3424)
                            .name("Jour Hirseweckerl ab30Stk.HB")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt spinattascheab30Stueck =
                    Produkt.builder()
                            .id(338)
                            .name("Jour Spinattasche ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt spinattascheab30StueckHB =
                    Produkt.builder()
                            .id(3338)
                            .name("Jour Spinattasche ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt lauchtascheab30Stueck =
                    Produkt.builder()
                            .id(463)
                            .name("Jour Lauchtasche ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt lauchtascheab30StueckHB =
                    Produkt.builder()
                            .id(3463)
                            .name("Jour Lauchtasche ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt briochekipferlab20Stueck =
                    Produkt.builder()
                            .id(21)
                            .name("Jour Briochekipferl ab20Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt kipferlab20Stueck =
                    Produkt.builder()
                            .id(34)
                            .name("Jour Kipferl ab20Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourTopfengolatscheStueck =
                    Produkt.builder()
                            .id(81)
                            .name("Jour Topfengolatsche Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourTopfengolatscheStueckHB =
                    Produkt.builder()
                            .id(3081)
                            .name("Jour Topfengolatsche Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourNusskipferStueck =
                    Produkt.builder()
                            .id(88)
                            .name("Jour Nusskipfer Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourNusskipferStueckHB =
                    Produkt.builder()
                            .id(3088)
                            .name("Jour Nusskipfer Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourMohnkipferab20Stueck =
                    Produkt.builder()
                            .id(122)
                            .name("Jour Mohnkipfer ab20Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourMohnkipferab20StueckHB =
                    Produkt.builder()
                            .id(3122)
                            .name("Jour Mohnkipfer ab20Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourSchokostangerlab20Stueck =
                    Produkt.builder()
                            .id(184)
                            .name("Jour Schokostangerl ab20Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourSchokostangerab20StueckHB =
                    Produkt.builder()
                            .id(3184)
                            .name("Jour Mohnkipfer ab20Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourKirschpolsterab20Stueck =
                    Produkt.builder()
                            .id(185)
                            .name("Jour Kirschpolster ab20Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourKirschpolsterab20StueckHB =
                    Produkt.builder()
                            .id(3185)
                            .name("Jour Kirschpolster ab20Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt yourVanillestangerlab20Stueck =
                    Produkt.builder()
                            .id(291)
                            .name("Jour Vanillestangerl ab20Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourVanillestangerlab20StueckHB =
                    Produkt.builder()
                            .id(3291)
                            .name("Jour Vanillestangerl ab20Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourKastanienzipfab20Stueck =
                    Produkt.builder()
                            .id(312)
                            .name("Jour Kastanienzipf ab20Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourKastanienzipfab20StueckHB =
                    Produkt.builder()
                            .id(3312)
                            .name("Jour Kastanienzipf ab20Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourNusstascherlab30Stueck =
                    Produkt.builder()
                            .id(195)
                            .name("Jour Nusstascherl ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourNusstascherlab30StueckHB =
                    Produkt.builder()
                            .id(3195)
                            .name("Jour Nusstascherl ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourPowidlgolatscheab30Stueck =
                    Produkt.builder()
                            .id(219)
                            .name("Jour Powidlgolatsche ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();


            Produkt yourPowidlgolatscheab30StueckHB =
                    Produkt.builder()
                            .id(3219)
                            .name("Jour Powidlgolatsche ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourTopfengolatscheab30Stueck =
                    Produkt.builder()
                            .id(289)
                            .name("Jour Topfengolatsche ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourTopfengolatscheab30StueckHB =
                    Produkt.builder()
                            .id(3289)
                            .name("Jour Topfengolatsche ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourSchokostangerab30Stueck =
                    Produkt.builder()
                            .id(355)
                            .name("Jour Schokostangerl ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourSchokostangerab30StueckHB =
                    Produkt.builder()
                            .id(3355)
                            .name("Jour Schokostangerl ab30StkHB.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourRosinenweckerlab30Stueck =
                    Produkt.builder()
                            .id(375)
                            .name("Jour Rosinenweckerl ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourRosinenweckerlab30StueckHB =
                    Produkt.builder()
                            .id(3375)
                            .name("Jour Rosinenweckerl ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourZauberkipferlab30Stueck =
                    Produkt.builder()
                            .id(426)
                            .name("Jour Zauberkipferl ab30Stk.")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(jourGebaeck)
                    .build();

            Produkt yourZauberkipferlab30StueckHB =
                    Produkt.builder()
                            .id(3426)
                            .name("Jour Zauberkipferl ab30Stk.HB")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(jourGebaeck)
                    .build();



            Produkt vkMinipinzeMitMarmelade =
                    Produkt.builder()
                            .id(462)
                            .name("VK Minipinze mit Marmelade")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkMinipinzeOhnMarmelade =
                    Produkt.builder()
                            .id(446)
                            .name("VK Minipinze ohne Marmelade")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkOsterlammOhneRosinenKlein =
                    Produkt.builder()
                            .id(161)
                            .name("VK Osterlamm ohne rosinen klein")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkOsterlammGross =
                    Produkt.builder()
                            .id(163)
                            .name("VK Osterlamm Groß")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkOsterhase =
                    Produkt.builder()
                            .id(164)
                            .name("VK Osterhase")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkOsterei =
                    Produkt.builder()
                            .id(425)
                            .name("VK Osterei")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkSacherei =
                    Produkt.builder()
                            .id(967)
                            .name("VK Sacherei")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkBriochhase =
                    Produkt.builder()
                            .id(919)
                            .name("VK Briochhase")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkOsterpinzeGross =
                    Produkt.builder()
                            .id(165)
                            .name("VK Osterpinze Groß")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkKrapfenhase =
                    Produkt.builder()
                            .id(107)
                            .name("VK Krapfenhase")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkOsternestMitBioei =
                    Produkt.builder()
                            .id(372)
                            .name("VK Osternest mit Bioei")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt vkOsterkipferlOhneBestreuung =
                    Produkt.builder()
                            .id(267)
                            .name("VK Osterkipferl ohne Bestreuung")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt osterpinze =
                    Produkt.builder()
                            .id(20)
                            .name("Osterpinze")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt osterlamm =
                    Produkt.builder()
                            .id(218)
                            .name("Ostelamm")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt weihbrot =
                    Produkt.builder()
                            .id(254)
                            .name("Weihbrot")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt OsterlammGross =
                    Produkt.builder()
                            .id(283)
                            .name("Osterlamm Gross")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt ostereierBunt =
                    Produkt.builder()
                            .id(401)
                            .name("Ostereier Bunt")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt osternest =
                    Produkt.builder()
                            .id(455)
                            .name("Osternest")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt osterkipferlMit05kg =
                    Produkt.builder()
                            .id(460)
                            .name("Osterkipferl mit 0,5 kg")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt minipinzeohneMarmelade =
                    Produkt.builder()
                            .id(466)
                            .name("Mini Pinze ohne Marmelde")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt osterSackerl =
                    Produkt.builder()
                            .id(2690)
                            .name("Oster Sackerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt krapfenhaseMarille =
                    Produkt.builder()
                            .id(105)
                            .name("Krapfenhase Marille")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt osterhase =
                    Produkt.builder()
                            .id(217)
                            .name("Osterhase")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt bioosterei =
                    Produkt.builder()
                            .id(249)
                            .name("Bio Osterei")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt minipinzeNormalmitMarmelade =
                    Produkt.builder()
                            .id(456)
                            .name("Mini Pinze normal mit Marmelade")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();

            Produkt OsterkipferlOhne05kg =
                    Produkt.builder()
                            .id(468)
                            .name("Osterkipferl ohne 0,5")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(ostern)
                            .build();


            Produkt vkSchokoherz =
                    Produkt.builder()
                            .id(261)
                            .name("VK Schokoherz")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt vkBriochherzGeflochten =
                    Produkt.builder()
                            .id(310)
                            .name("VK Briochherz geflochten")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt vkBriochherzBio =
                    Produkt.builder()
                            .id(376)
                            .name("VK Briochherz Bio")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt vkSchokoOrangenherz =
                    Produkt.builder()
                            .id(812)
                            .name("VK Schoko-Orangenherz")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();


            Produkt vkSchokotorte =
                    Produkt.builder()
                            .id(157)
                            .name("VK Schokotorte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt vkBriochherz =
                    Produkt.builder()
                            .id(317)
                            .name("VK Briochherz")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt vkHerzkrapfen =
                    Produkt.builder()
                            .id(546)
                            .name("VK Herzkrapfen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();


            Produkt vkLinzertorteHerz =
                    Produkt.builder()
                            .id(752)
                            .name("VK Linzertorte Herz")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt briochherz =
                    Produkt.builder()
                            .id(35)
                            .name("Briochherz")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt briochherzGefuelltOhnZucker =
                    Produkt.builder()
                            .id(461)
                            .name("Briochherz gefüllt ohnr Zucker")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt herzkrapfen =
                    Produkt.builder()
                            .id(583)
                            .name("Herz Krapfen")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();

            Produkt briochherzGefuelltMitZucker =
                    Produkt.builder()
                            .id(20)
                            .name("Briochherz gefüllt mit Zucker")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(muttertag)
                            .build();


            Produkt vkBioTeegebaeck250g =
                    Produkt.builder()
                            .id(108)
                            .name("VK Bio Teegebäck 250g")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkNikololebkuchen =
                    Produkt.builder()
                            .id(166)
                            .name("VK Nikololebkuchen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkChriststollen500g =
                    Produkt.builder()
                            .id(169)
                            .name("VK Christstollen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkKletzenbrot500g =
                    Produkt.builder()
                            .id(174)
                            .name("VK Kletzenbrot 500g")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkTeegebaeckVegan =
                    Produkt.builder()
                            .id(231)
                            .name("VK Teegebäck vegan")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkButternuesse =
                    Produkt.builder()
                            .id(257)
                            .name("VK Butternüsse")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vollwertZimtsterne =
                    Produkt.builder()
                            .id(292)
                            .name("Vollwert Zimtsterne")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkDinkelLebkuchenbaum =
                    Produkt.builder()
                            .id(294)
                            .name("VK D-Lebkuchenbaum")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkDinkelAdventstollen500g =
                    Produkt.builder()
                            .id(298)
                            .name("VK D-Adventstollen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkLebkuchenGemischt =
                    Produkt.builder()
                            .id(359)
                            .name("VK Lebkuchen Gemischt")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkAdventstollenDiabetiker =
                    Produkt.builder()
                            .id(388)
                            .name("VK Adventstollen Diabetiker")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkLebkuchenNatur =
                    Produkt.builder()
                            .id(421)
                            .name("VK Lebkuchen Natur")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkBriochkrampus =
                    Produkt.builder()
                            .id(445)
                            .name("VK Briochkrampus")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkLebkuchenSchoko =
                    Produkt.builder()
                            .id(857)
                            .name("VK Lebkuchen Schoko")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkBioTeegebaeck500g =
                    Produkt.builder()
                            .id(138)
                            .name("VK Bio Teegebäck 500g")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkKrampuslebkuchen =
                    Produkt.builder()
                            .id(167)
                            .name("VK Krampuslebkuchen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkDinkelChriststollen380g =
                    Produkt.builder()
                            .id(170)
                            .name("VK D-Christstollen 380g")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkVanillekipferl =
                    Produkt.builder()
                            .id(246)
                            .name("VK Vanillekipferl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkDinkelChriststollen650g =
                    Produkt.builder()
                            .id(284)
                            .name("VK D-Christstollen 650g")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vollwertZimtsternbaum =
                    Produkt.builder()
                            .id(293)
                            .name("VK Vollwert Zimtsternbaum")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkWeihnachtsSchachtel=
                    Produkt.builder()
                            .id(296)
                            .name("VK Weihnachts Schachtel")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkDinkelSacherwuerfelEiHerz =
                    Produkt.builder()
                            .id(357)
                            .name("VK D-Sacherw/Ei/Herz")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();


            Produkt vkBioTeegebaeckLinzeraugen =
                    Produkt.builder()
                            .id(360)
                            .name("VK Bio Teeg Linzeraugen")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkLebkuchenteig750g =
                    Produkt.builder()
                            .id(387)
                            .name("VK Lebkuchenteig 750g")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkVanillekipferlTeig =
                    Produkt.builder()
                            .id(400)
                            .name("VK Vanillekipferl-teig")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkLebkuchenSchneemann =
                    Produkt.builder()
                            .id(423)
                            .name("VK Lebkuchen Schneemann")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkBioTeegebaeckSchoko =
                    Produkt.builder()
                            .id(432)
                            .name("VK Bio Teegebäck Schoko")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkWeihnachtsstern =
                    Produkt.builder()
                            .id(964)
                            .name("VK Weihnachtsstern")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();


            Produkt vkChriststollengross =
                    Produkt.builder()
                            .id(169)
                            .name("VK Christstollen Groß")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkLebkuchentaler =
                    Produkt.builder()
                            .id(374)
                            .name("VK Lebkuchentaler")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt lichtinDunkelSterne=
                    Produkt.builder()
                            .id(431)
                            .name("Licht ins Dunkel Sterne")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt briochkrampus =
                    Produkt.builder()
                            .id(439)
                            .name("Briochkrampus")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();


            Produkt vkLebkuchenEngel=
                    Produkt.builder()
                            .id(469)
                            .name("VK Lebkuchen Engel")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();


            Produkt vkLebkuchenBaum=
                    Produkt.builder()
                            .id(481)
                            .name("VK Lebkuchen Baum")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkDinkelWeihnachtstorte=
                    Produkt.builder()
                            .id(484)
                            .name("VK Dinkel Weihnachtstorte")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt teegebaeckDinkel500kg=
                    Produkt.builder()
                            .id(488)
                            .name("Teegebäck Dinkel 500kg")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt teegebaeckDinkel250kg=
                    Produkt.builder()
                            .id(490)
                            .name("Teegebäck Dinkel 250kg")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vanillekipfer150g=
                    Produkt.builder()
                            .id(487)
                            .name("Vanillekipfer 150g")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkVanillekipfer1kg=
                    Produkt.builder()
                            .id(482)
                            .name("VK Vanillekipfer 1kg")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkDinkelFruechtelebkuchen=
                    Produkt.builder()
                            .id(465)
                            .name("VK Dinkel-Früchtelebkuchen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();


            Produkt vkDBadTatzmFruechtestollen=
                    Produkt.builder()
                            .id(442)
                            .name("VK D-BadTatzm.Früchtestollen")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();

            Produkt vkMandelkekse250g=
                    Produkt.builder()
                            .id(212)
                            .name("VK Mandelkekse 250g")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(weihnachten)
                            .build();


            Produkt vkLauchTofutascherl =
                    Produkt.builder()
                            .id(149)
                            .name("VK LauchTofutascherl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt vkLauchTofutascherlHB =
                    Produkt.builder()
                            .id(2149)
                            .name("VK LauchTofutascherlHB (3)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(snack)
                            .build();

            Produkt vkTomatenMozzarellatscherltascherl =
                    Produkt.builder()
                            .id(272)
                            .name("VK Tomaten Mozzarellatascherl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt vkTomatenMozzarellatscherltascherlHB =
                    Produkt.builder()
                            .id(2272)
                            .name("VK Tomaten MozzarellatascherlHB (3)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(snack)
                            .build();

            Produkt vkSpinatSchafkaesetascherltascherl =
                    Produkt.builder()
                            .id(302)
                            .name("VK SpinatSchafkäsetascherl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt vkSpinatSchafkaesetascherltascherlHB =
                    Produkt.builder()
                            .id(2302)
                            .name("VK SpinatSchafkäsetascherl HB (3)")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(true)
                            .produktgruppe(snack)
                            .build();


            Produkt vkPizzaSchinkenMais =
                    Produkt.builder()
                            .id(2010)
                            .name("VK SchinkenMais Pizza")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt vkPizzaGemuese =
                    Produkt.builder()
                            .id(2012)
                            .name("VK Gemüse Pizza")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt steinpilzErdaepfelLauchsuppe =
                    Produkt.builder()
                            .id(949)
                            .name("Strinpilz-Erdäpfel-Lauchsuppe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt spargelcremesuppe =
                    Produkt.builder()
                            .id(803)
                            .name("Spargelcremesuppe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt karottenKokosIngwersuppe=
                    Produkt.builder()
                            .id(986)
                            .name("Karotten-Kokos-Ingwersuppe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt baerlauchsuppe =
                    Produkt.builder()
                            .id(802)
                            .name("Bärlauchsuppe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt suesskartoffelsuppe =
                    Produkt.builder()
                            .id(918)
                            .name("Süßkartoffelsuppe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt kuerbiscremesuppe =
                    Produkt.builder()
                            .id(945)
                            .name("Kürbiscremesuppe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt erbsensuppe =
                    Produkt.builder()
                            .id(816)
                            .name("Erbsensuppe")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt vkGemuesestrudel =
                    Produkt.builder()
                            .id(1622)
                            .name("VK Gemüsestrudel")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();



            Produkt vkBroccholiKarfiolstrudel =
                    Produkt.builder()
                            .id(182)
                            .name("VK Beoccholi-Karfiolstrudel")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt vitaljoghurt =
                    Produkt.builder()
                            .id(265)
                            .name("Vitaljoghurt")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt chiaJoghurt =
                    Produkt.builder()
                            .id(265)
                            .name("Chia Joghurt")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt kuerbisBalsamicodressing =
                    Produkt.builder()
                            .id(391)
                            .name("Kürbis-Balsamicodressing")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt joghurtdressing =
                    Produkt.builder()
                            .id(556)
                            .name("Joghurtdressing")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt grischischerSalat =
                    Produkt.builder()
                            .id(703)
                            .name("Grichischer Salat")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt thunfischSalat =
                    Produkt.builder()
                            .id(701)
                            .name("Thunfisch Salat")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt huehnerstreifenSalat =
                    Produkt.builder()
                            .id(870)
                            .name("Hühnerstreifen Salat")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt nudelsalat =
                    Produkt.builder()
                            .id(900)
                            .name("Nudelsalat")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt herbstsalat =
                    Produkt.builder()
                            .id(580)
                            .name("Herbstsalat")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt chillikaeseSnack =
                    Produkt.builder()
                            .id(787)
                            .name("Chillikäse Snack")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt dKornweckerlOhneHefeSchafkaese =
                    Produkt.builder()
                            .id(551)
                            .name("VK Dinkelkornweckerl ohne Hefe mit Schafkäse")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt lachsweckerl =
                    Produkt.builder()
                            .id(868)
                            .name("Lachsweckerl")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt humussnaek =
                    Produkt.builder()
                            .id(224)
                            .name("Humussnäck")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt dinkelkaeseweckerlMitEmmentaler =
                    Produkt.builder()
                            .id(693)
                            .name("VK Dinkelkäseweckerl mit Emmentaler")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt laugenCammenbertweckerl =
                    Produkt.builder()
                            .id(385)
                            .name("Laugen-Cammenbertweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt kornspitzSchinkenweckerl =
                    Produkt.builder()
                            .id(550)
                            .name("Kornspitzschinkenweckerl")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt prosciuttosnack =
                    Produkt.builder()
                            .id(499)
                            .name("ProsciuttoSnack")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt grahamEiSnack =
                    Produkt.builder()
                            .id(871)
                            .name("Graham Ei-Snack")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt pharaoweckerlMitTofu =
                    Produkt.builder()
                            .id(548)
                            .name("Pharaoweckerl mit Tofu")
                            .bio(false)
                            .mehl(Mehl.Pharao)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt karottenCurrySnack =
                    Produkt.builder()
                            .id(690)
                            .name("Karotten-Curry Snack")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();

            Produkt schinkenKaesecroissant =
                    Produkt.builder()
                            .id(639)
                            .name("Schinken-Käsecroissant")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt vitalsnack =
                    Produkt.builder()
                            .id(452)
                            .name("Vitalsnack")
                            .bio(false)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt heurigensnack =
                    Produkt.builder()
                            .id(229)
                            .name("Heurigensnack (Hirseweckerl mit Linsenaufstrich")
                            .bio(true)
                            .mehl(Mehl.Dinkel)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(snack)
                            .build();


            Produkt mistschaufelUndBsesen  =
                    Produkt.builder()
                            .id(1970)
                            .name("Mistschaufel und Besen")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt putzbuersteOval  =
                    Produkt.builder()
                            .id(1975)
                            .name("Putzbürste Oval")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt geschirrtuecher =
                    Produkt.builder()
                            .id(1979)
                            .name("GESCHIRRTÜCHER")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt kuechenrollen4er =
                    Produkt.builder()
                            .id(1913)
                            .name("Küchenrollen 4er")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt wettexTuecher =
                    Produkt.builder()
                            .id(1914)
                            .name("Wettex Tücher")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt frischhaltefolie =
                    Produkt.builder()
                            .id(1924)
                            .name("Frischhaltefolie")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt mikrofasertuecher =
                    Produkt.builder()
                            .id(1922)
                            .name("Microfasertücher")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt bodenMicrofasertuch =
                    Produkt.builder()
                            .id(1923)
                            .name("Boden Microfasertuch")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt teppich80x120 =
                    Produkt.builder()
                            .id(1984)
                            .name("Teppich 80x120")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt rollenhandtuchBlau =
                    Produkt.builder()
                            .id(1903)
                            .name("Rollenhandtuch Blau")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt falthandtuchFuerSpaender =
                    Produkt.builder()
                            .id(1921)
                            .name("Falthandtuch für Spender")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt abwaschschwaemme =
                    Produkt.builder()
                            .id(1969)
                            .name("Abwaschschwämme")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt reziTopfreiniger =
                    Produkt.builder()
                            .id(1968)
                            .name("rezi Topfreiniger")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt spuckschutzfuerVerkostung =
                    Produkt.builder()
                            .id(1966)
                            .name("Spuckschutz für Verkostung")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt spuckschutz =
                    Produkt.builder()
                            .id(1933)
                            .name("Spuckschutz")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(true)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt einweghandschuheL =
                    Produkt.builder()
                            .id(1986)
                            .name("Einweghandschuhe L")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt einweghandschuheM  =
                    Produkt.builder()
                            .id(1987)
                            .name("Einweghandschuhe M")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(organisatorisch)
                            .build();

            Produkt plastikhandschuhe  =
                    Produkt.builder()
                            .id(1905)
                            .name("Plastikhandschuhe")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt ofenhandschuhe  =
                    Produkt.builder()
                            .id(1939)
                            .name("Ofenhandschuhe")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt gebaeckszangeLang  =
                    Produkt.builder()
                            .id(1982)
                            .name("Gebäckszange Lang")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt gebaeckszangeKurz =
                    Produkt.builder()
                            .id(1983)
                            .name("Gebäckszange Kurz")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt geldsackerlRot =
                    Produkt.builder()
                            .id(1985)
                            .name("Geldsackerl Rot")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt geschenkband =
                    Produkt.builder()
                            .id(1943)
                            .name("Geschenkband")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt a4PaoierFax =
                    Produkt.builder()
                            .id(2645)
                            .name("A4 Papier Fax")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt kassarollen =
                    Produkt.builder()
                            .id(1878)
                            .name("Kassarollen")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt gradwohlEtikettenKl =
                    Produkt.builder()
                            .id(1938)
                            .name("Gradwohl Etiketten kl.")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt prospektInternetshop =
                    Produkt.builder()
                            .id(1948)
                            .name("Prospekt Internetshop")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt sammelpaesse =
                    Produkt.builder()
                            .id(100)
                            .name("Sammelpässe")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt allergieliste =
                    Produkt.builder()
                            .id(1894)
                            .name("Allergieliste")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt waldhonig450g =
                    Produkt.builder()
                            .id(1402)
                            .name("Waldhonig 450g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt waldhonig220g =
                    Produkt.builder()
                            .id(1401)
                            .name("Waldhonig 220g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt bluetencremehonig450g =
                    Produkt.builder()
                            .id(1408)
                            .name("Blütencremehobig 450g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                            .build();

            Produkt bluetencremehonig220g =
                    Produkt.builder()
                            .id(1407)
                            .name("Blütencremehobig 220g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                            .build();

            Produkt lindenbluetenhonig450g =
                    Produkt.builder()
                            .id(1404)
                            .name("Lindenblütenhonig 450g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt lindenbluetenhonig220g =
                    Produkt.builder()
                            .id(1403)
                            .name("Lindenblütenhonig 220g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt akazienhonig450g =
                    Produkt.builder()
                            .id(1406)
                            .name("Akazienhonig 450g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt akazienhonig220g =
                    Produkt.builder()
                            .id(1405)
                            .name("Akazienhonig 220g")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt portionsmarmelade =
                    Produkt.builder()
                            .id(1149)
                            .name("Portions Marmelade")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(organisatorisch)
                            .build();

            Produkt zwetschkenMarelade =
                    Produkt.builder()
                            .id(2657)
                            .name("Zwetschken Marmelade")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(organisatorisch)
                            .build();


            Produkt erdbeerMarelade =
                    Produkt.builder()
                            .id(2658)
                            .name("Erdbeer Marmelade")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                            .produktgruppe(organisatorisch)
                            .build();


            Produkt weichselMarelade =
                    Produkt.builder()
                            .id(2629)
                            .name("Weichsel Marmelade")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt marillenMarelade =
                    Produkt.builder()
                            .id(2660)
                            .name("Marillen Marmelade")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt waldheidelbeerMarelade =
                    Produkt.builder()
                            .id(2661)
                            .name("Waldheidelbeer Marmelade")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt schokoKirschMarelade =
                    Produkt.builder()
                            .id(2659)
                            .name("Schoko/Kirsch Marmelade")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt becherFrischGebresterSaft =
                    Produkt.builder()
                            .id(2664)
                            .name("Becher frisch gepr.Säfte(MH/LST")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt koffeinfreiHaag250g =
                    Produkt.builder()
                            .id(1839)
                            .name("Koffeinfrei Haag")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt kaffeeExcelsior1kg =
                    Produkt.builder()
                            .id(1842)
                            .name("Kaffee Excelsior 1kg")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt trinkschokoPompadurWMF1kg=
                    Produkt.builder()
                            .id(1840)
                            .name("WMF Trinkschoko Pompadur 1kg")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt klDeckelfKaffeeb =
                    Produkt.builder()
                            .id(1945)
                            .name("kl.Deckel für Kaffeeb. ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt mittelDeckelfKaffeeb =
                    Produkt.builder()
                            .id(1951)
                            .name("mittelere Deckel für Kaffeeb. ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt grDeckelfKaffeeb =
                    Produkt.builder()
                            .id(1952)
                            .name("gr.Deckel für Kaffeeb. ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt klKaffeeb =
                    Produkt.builder()
                            .id(1841)
                            .name("kl. Kaffeeb. ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt mittlereKaffeeb =
                    Produkt.builder()
                            .id(1843)
                            .name("mittlere Kaffeeb. ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt geKaffeeb =
                    Produkt.builder()
                            .id(1844)
                            .name("gr. Kaffeeb. ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt kaffeebecherhalter2er =
                    Produkt.builder()
                            .id(1845)
                            .name("Kaffeebecherhalter 2er ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt kaffeestaebchen=
                    Produkt.builder()
                            .id(1846)
                            .name("Kaffeestäbchen")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt plastikLoeffelGr =
            Produkt.builder()
                    .id(1932)
                    .name("Plastik Löffel groß")
                    .bio(false)
                    .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt plastikGabelGR =
            Produkt.builder()
                    .id(1941)
                    .name("Plastik Gabel groß")
                    .bio(false)
                    .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();



            Produkt plastiMesserGR =
            Produkt.builder()
                    .id(1942)
                    .name("Plastik Messer groß")
                    .bio(false)
                    .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt strohhalme =
                    Produkt.builder()
                            .id(2068)
                            .name("Strohhalme")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt zuckerportionen =
                    Produkt.builder()
                            .id(1849)
                            .name("Zuckerportionen")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt ruebenzuckerPortionen =
                    Produkt.builder()
                            .id(1850)
                            .name("Rübenzucker Portionen")
                            .bio(true)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt bruanerPorionszucker =
                    Produkt.builder()
                            .id(1851)
                            .name("Brauner Porionszucker")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt kandisinPortioniert =
                    Produkt.builder()
                            .id(1852)
                            .name("Kandisin Portioniert")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt tragtaschenNeu =
                    Produkt.builder()
                            .id(854)
                            .name("Tragtaschen Neu")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt stofftaschen =
                    Produkt.builder()
                            .id(758)
                            .name("Stofftaschen")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt sackerl1kg =
                    Produkt.builder()
                            .id(1856)
                            .name("Sackerl 1kg")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt sackerl15kg =
                    Produkt.builder()
                            .id(1857)
                            .name("Sackerl 1,5kg")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt sackerl2kg =
                    Produkt.builder()
                            .id(1858)
                            .name("Sackerl 2kg")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt sackerl35kg =
                    Produkt.builder()
                            .id(1859)
                            .name("Sackerl 3,5kg")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt pergaminsackerl =
                    Produkt.builder()
                            .id(1860)
                            .name("Pergaminsackerl")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt snacksackerlGelocht =
                    Produkt.builder()
                            .id(1861)
                            .name("Snacksackerl gelocht")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt baguettesackerl =
                    Produkt.builder()
                            .id(1862)
                            .name("Baguettesackerl")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt cellophansackerl =
                    Produkt.builder()
                            .id(1940)
                            .name("Cellophansackerl")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt pappteller10x16 =
                    Produkt.builder()
                            .id(1864)
                            .name("Pappteller 10x16")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt pappteller13x20 =
                    Produkt.builder()
                            .id(1865)
                            .name("Pappteller 13x20")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt pappteller16x23 =
                    Produkt.builder()
                            .id(1866)
                            .name("Pappteller 16x23")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt blechpapierGeschnitten =
                    Produkt.builder()
                            .id(1870)
                            .name("Blechpapier Geschnitten")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt servietten =
                    Produkt.builder()
                            .id(1871)
                            .name("Servietten")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt tortenspitzenpapier =
                    Produkt.builder()
                            .id(1931)
                            .name("Tortenspitzenpapier")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt tortenpapierDurchsichtigGeschnitten =
                    Produkt.builder()
                            .id(1872)
                            .name("Tortenpapier durchsichtig geschnitten")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt bedrucktesGradwohlpapier =
                    Produkt.builder()
                            .id(1873)
                            .name("Bedrucktes Gradwohlpapier")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt geschenkfolieBedruckt =
                    Produkt.builder()
                            .id(1874)
                            .name("Geschenkfolie Bedruckt")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt plastiksackerlZumAbreissenKlein =
                    Produkt.builder()
                            .id(1875)
                            .name("PLastiksackerl Zum Abreißen klein")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt plastiksackerlZumAbreissenMittel =
                    Produkt.builder()
                            .id(1876)
                            .name("PLastiksackerl Zum Abreißen mittel")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt plastiksackerlZumAbreissenGross =
                    Produkt.builder()
                            .id(1877)
                            .name("PLastiksackerl Zum Abreißen groß")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt tortenkartonGefaltenGr4 =
                    Produkt.builder()
                            .id(1885)
                            .name("Tortenkarton gefalten Gr.4(groß)")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt tortenkartonGefaltenGr3 =
                    Produkt.builder()
                            .id(1886)
                            .name("Tortenkarton gefalten Gr.3")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt tortenkartonGefaltenGr2 =
                    Produkt.builder()
                            .id(1887)
                            .name("Tortenkarton gefalten Gr.2")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt tortenkartonGefaltenGr1 =
                    Produkt.builder()
                            .id(1888)
                            .name("Tortenkarton gefalten Gr.1")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();



            Produkt krapfenschachtelKL =
            Produkt.builder()
                    .id(1936)
                    .name("Krapfenschachtel klein")
                    .bio(false)
                    .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt krapfenschachtelGr =
            Produkt.builder()
                    .id(1937)
                    .name("Krapfenschachtel groß")
                    .bio(false)
                    .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt dieKraftDerKoerner =
                    Produkt.builder()
                            .id(1891)
                            .name("Die Kraft der Körner ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt bleche60x40 =
                    Produkt.builder()
                            .id(1896)
                            .name("Bleche 60x40 ")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt hafermilch1Liter =
                    Produkt.builder()
                            .id(565)
                            .name("Hafermilch 1 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt sojamilch1Liter =
                    Produkt.builder()
                            .id(566)
                            .name("Sojamilch 1 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt zwergenwieseBasillikum =
                    Produkt.builder()
                            .id(575)
                            .name("Zwergenwiese Basilikum")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt zwergenwieseMeerettich =
                    Produkt.builder()
                            .id(576)
                            .name("Zwergenwiese Meerettich")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt zwergenwiesePaprika =
                    Produkt.builder()
                            .id(577)
                            .name("Zwergenwiese Paprika")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt zwergenwieseSchnittlauch =
                    Produkt.builder()
                            .id(578)
                            .name("Zwergenwiese Schnittlauch")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt kalkreiniger1Liter =
                    Produkt.builder()
                            .id(1981)
                            .name("Kalkreiniger 1 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();



            Produkt feinsteinzeugreiniger1Liter =
                    Produkt.builder()
                            .id(1904)
                            .name("Feinsteinzeugreiniger 1 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt uNI810Allzwekreiniger1Liter =
                    Produkt.builder()
                            .id(1910)
                            .name("UNI810 Allzwekreiniger 1 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt danclorKanister =
                    Produkt.builder()
                            .id(2089)
                            .name("Danclor Kanister")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt holluvitDuroKanister =
                    Produkt.builder()
                            .id(1897)
                            .name("Holluvit Duro Kanister")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt holluGlanzreinigerKanister =
                    Produkt.builder()
                            .id(1898)
                            .name("Hollu Glanzreiniger Kanister")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt ofenProCleaner2in1 =
                    Produkt.builder()
                            .id(1895)
                            .name("Ofen Pro Cleaner 2in1")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt wmfEntkalker =
                    Produkt.builder()
                            .id(2673)
                            .name("WMF Entkalker")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt wmfRinigungstabletten =
                    Produkt.builder()
                            .id(1900)
                            .name("WMF Reinigungstabletten")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt wmfMilchreiniger =
                    Produkt.builder()
                            .id(1901)
                            .name("WMF Milchreiniger")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt muellsaecke35Liter =
                    Produkt.builder()
                            .id(1907)
                            .name("Müllsäcke 35 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt muellsaecke110Liter =
                    Produkt.builder()
                            .id(1908)
                            .name("Müllsäcke1 10 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt muellsaecke150Liter =
                    Produkt.builder()
                            .id(1909)
                            .name("Müllsäcke 150 Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt priBlau =
                    Produkt.builder()
                            .id(1925)
                            .name("Pril Blau")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt tabsGeschirrspueler =
                    Produkt.builder()
                            .id(1916)
                            .name("TabsGeschirrspüler")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt salzGeschirrspueler =
                    Produkt.builder()
                            .id(1917)
                            .name("SalzGeschirrspüler")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt klarspuelerGeschirrspueler =
                    Produkt.builder()
                            .id(1918)
                            .name("KlarspülerGeschirrspüler")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt seifeFuerSpender =
                    Produkt.builder()
                            .id(1920)
                            .name("Seife für Spender")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            Produkt wcReiniger1Liter =
                    Produkt.builder()
                            .id(2677)
                            .name("WcReiniger1Liter")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt pissoirEinleger =
                    Produkt.builder()
                            .id(2676)
                            .name("Pissoie Einleger")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt blueStarEinhaenger =
                    Produkt.builder()
                            .id(1977)
                            .name("BlueStar Einhänger")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();


            Produkt blueStarNachfuellung =
                    Produkt.builder()
                            .id(1978)
                            .name("BlueStar nachfüllung")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();
            
            Produkt duftsprueherNachfuellung =
                    Produkt.builder()
                            .id(2678)
                            .name("Duftsprüher Nachfüllung")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();

            Produkt wcPapier10er =
                    Produkt.builder()
                            .id(1919)
                            .name("WC Papier 10er")
                            .bio(false)
                            .mehl(null)
                            .mehlMischung(null)
                            .hb(false)
                    .produktgruppe(organisatorisch)
                    .build();







            List<Produkt> produkte = Arrays.asList(
                    bauernbrot, roggenbrot, dinkelbrotOhneHefe, pharaobrotOhneHefe, dinkelbrot, dinkeltoast,
                    leinsamenAmaranthbrotOhneHefe, backfermentbrotOhneHefe, karottenNussbrot, kraftbrotOhneHefe,
                    chiabrotOhneHefe, dinkelbrotStange, bauernbrotStange, siebenkornStange, kuerbiskernbrot,
                    suesskartoffelbrot, olivenbrot, roteRuebenbrot, zwiebelbrot, nussbrotstange, nussbrotleibRund,
                    baerlauchbrot, sandwich, baguette, kaesestangerl, vitalwecker, olinebweckerl, dinkelsemmel,
                    dinkelkornsitz, dinkelkornspitzSalz, dinkelweckerl,  dinkelkornspitzOhneBestreu, pharaoweckerl,
                    chiaweckerl, dinkellaugenstangerl, hirseweckerl, salzstangerl, salzbrezerlKlein,
                    pharaoamaranthweckerOhnHefe, kornweckerlOhneHefe, dinkelweckerlOhneHefe, foccachia,
                    kuerbislaibchen, basilikumweckerl, kuemmelweckerl, baerlauchweckerl, kurkumaweckerl,
                    rosinenweckerl, nussMandelweckerl, studentenfutter, dinkelSchnecke, pharaobriochestriezerl,
                    topfengolatsche, powidlgolatsche, nusskipferl, mohnkipfer, topfenmarille, dinkelcroissant,
                    Schokostanger, pharaocroissant, ribiselstangerl, nussino, kastanienzipf, kirschpolster,
                    apfeltascherl, birnentascherl, marzipankrone, kuerbiskipfer, fruechteplunder, marillenKrapfen,
                    vanillekKapfen, powidlKrapfen, marillenKrapfenVegan, marillenKrapfenYour, erdbeerkrapfen,
                    apfelsrtudel, topfensrtudel, marillensrtudel, birneMohnstrudel, obststrudel, egerlaender,
                    pharaoLinzerschnitte, obstfleck, birnenStreuselschnitte, apfelschnitte, mohnZitronenschnitte,
                    karottenroelchen, karottenZucchinischnitte, nussecke, linzerstanger, linzerauge, nougartherz,
                    sacherwuerfelHerzEi, schokoberg, striezelMitMandelspliter, striezelOhneBestreuung, bauernbrotHB,
                    dinkelbrotOhneHefeHB, dinkelbrotHB, leinsamenAmaranthbrotOhneHefeHB, karottenNussHB, kraftbrotOhneHefeHB,
                    chiabrotOhneHefeHB, kuerbislaibchenHB, kuerbisbrotHB, suesskartoffelbrotHB, olivenbrotHB, roteRuebenbrotHB,
                    zwiebelbrotHB, nussbrotlaibRundHB, baerlauchbrotHB, baguetteHB, kaesestangerlHB, vitalweckerHB,
                    olivenweckerlHB, dinkelsemmelHB, dinkelkornsitzHB, dinkelweckerlHB, dinkelkornspitzOhneBestreuHB, pharaoweckerlHB,
                    chiaweckerlHB, dinkellaugenstangerlHB, hirseweckerlHB, pharaoamaranthweckerOhnHefeHB, kornweckerlOhneHefeHB,
                    foccachiaHB, basilikumweckerlHB, kuemmelweckerlHB, rosinenweckerlHB, nussMandelweckerlHB, baerlauchweckerlHB,
                    dinkelweckerlOhneHefeHB, kurkumaweckerlHB, studentenfutterHB, dinkelSchneckeHB, pharaobriochestriezerlHB,
                    topfengolatscheHB, powidlgolatscheHB, nusskipferlHB, mohnkipferHB, topfenmarilleHB, dinkelcroissantHB,
                    schokostangerHB, pharaocroissantHB, ribiselstangerlHB, nussinoHB, kastanienzipfHB, kirschpolsterHB,
                    apfeltascherlHB, birnentascherlHB, kuerbiskipferHB, marzipankroneHB, jourSemmerl, jourSemmerlHB,
                    jourWachauer, jourWachauerHB, jourlaugenstanger, jourlaugenstangerHB, jourkaesestangerl, jourkaesestangerlHB,
                    jourSalzstangerl, grammelpogatscherl, grammelpogatscherlHB, jourMohnlaibchen, jourMohnlaibchenHB,
                    joursesamlaibchen, joursesamlaibchenHB, jourNussMandelweckerl, jourNussMandelweckerlHB, jourSalzstangerl,
                    jourSalzstangerlBioStk, jourPharaoweckerl, jourPharaoweckerlHB, jourKornspitz, jourKornspitzHB,
                    jourGrahamweckerl, jourGrahamweckerlHB, jourTomMozzTascherlab30StueckHB, jourTomMozzTascherlab30Stueck,
                    jourKuerbiskernweckerlab30StueckHB, jourKuerbiskernweckerlab30Stueck, Kaesestangerlab30Stueck,
                    Kaesestangerlab30StueckHB, hirseweckerlab30Stueck, hirseweckerlab30StueckHB, spinattascheab30StueckHB,
                    spinattascheab30Stueck, lauchtascheab30Stueck, lauchtascheab30StueckHB, briochekipferlab20Stueck,
                    kipferlab20Stueck, yourTopfengolatscheStueck, yourTopfengolatscheStueckHB, yourNusskipferStueck,
                    yourNusskipferStueckHB, yourNusskipferStueckHB, yourMohnkipferab20StueckHB, yourMohnkipferab20Stueck,
                    yourSchokostangerab20StueckHB, yourSchokostangerab30Stueck, yourSchokostangerlab20Stueck,
                    yourKirschpolsterab20Stueck, yourKirschpolsterab20StueckHB, yourVanillestangerlab20Stueck,
                    yourVanillestangerlab20StueckHB, yourKastanienzipfab20StueckHB, yourKastanienzipfab20Stueck,
                    yourNusstascherlab30StueckHB, yourNusstascherlab30Stueck, yourPowidlgolatscheab30StueckHB,
                    yourPowidlgolatscheab30Stueck, yourTopfengolatscheab30Stueck, yourTopfengolatscheab30StueckHB,
                    yourSchokostangerab30StueckHB, yourRosinenweckerlab30Stueck,
                    yourRosinenweckerlab30StueckHB, yourZauberkipferlab30StueckHB, yourZauberkipferlab30Stueck,
                    vkBioKuerbiskernbrotStange, vkMinipinzeOhnMarmelade,
                    vkOsterlammOhneRosinenKlein, vkOsterlammGross, vkOsterhase, vkOsterpinzeGross, vkBriochhase,
                    vkOsterei, vkSacherei, vkKrapfenhase, vkOsternestMitBioei, osterpinze, osterlamm, weihbrot,
                    vkOsterkipferlOhneBestreuung, ostereierBunt, osternest, OsterlammGross,
                    minipinzeohneMarmelade, osterSackerl, krapfenhaseMarille, osterhase, bioosterei, minipinzeNormalmitMarmelade,
                    osterkipferlMit05kg, vkSchokoherz, vkBriochherzGeflochten, vkBriochherz, vkSchokoOrangenherz,
                    vkHerzkrapfen, vkLinzertorteHerz, briochherz, briochherzGefuelltOhnZucker, OsterkipferlOhne05kg,
                    vkBriochherzBio, vkSchokotorte, briochherzGefuelltMitZucker, vkBioTeegebaeck250g, vkChriststollen500g,
                    vkNikololebkuchen, herzkrapfen, vkKletzenbrot500g, vkTeegebaeckVegan, vkButternuesse, vkDinkelAdventstollen500g,
                    vollwertZimtsterne, vkDinkelLebkuchenbaum, vkLebkuchenNatur, vkBriochkrampus, vkLebkuchenGemischt,
                    vkAdventstollenDiabetiker, vkLebkuchenSchoko, vkBioTeegebaeck500g, vkKrampuslebkuchen, vkDinkelChriststollen380g,
                    vkVanillekipferl, vkDinkelChriststollen650g, vollwertZimtsternbaum, vkWeihnachtsSchachtel, vkDinkelSacherwuerfelEiHerz,
                    vkBioTeegebaeckLinzeraugen, vkLebkuchenteig750g, vkVanillekipferlTeig, vkLebkuchenSchneemann, vkWeihnachtsstern,
                    vanillekipfer150g, teegebaeckDinkel250kg, teegebaeckDinkel500kg, vkDinkelFruechtelebkuchen, vkMandelkekse250g,
                    vkDBadTatzmFruechtestollen, vkVanillekipfer1kg, vkDinkelWeihnachtstorte, vkLebkuchenBaum, vkLebkuchenEngel,
                    briochkrampus, lichtinDunkelSterne, vkLebkuchentaler, vkChriststollengross, vkBioTeegebaeckSchoko,
                    vkMinipinzeMitMarmelade, vkLauchTofutascherl, vkLauchTofutascherlHB, vkTomatenMozzarellatscherltascherl,
                    vkTomatenMozzarellatscherltascherlHB, vkSpinatSchafkaesetascherltascherl, vkSpinatSchafkaesetascherltascherlHB,
                    vkPizzaSchinkenMais, vkPizzaGemuese, steinpilzErdaepfelLauchsuppe, spargelcremesuppe, karottenKokosIngwersuppe,
                    baerlauchsuppe, suesskartoffelsuppe, kuerbiscremesuppe, erbsensuppe, vkGemuesestrudel, vkBroccholiKarfiolstrudel,
                    vitaljoghurt, chiaJoghurt, kuerbisBalsamicodressing, joghurtdressing, grischischerSalat, thunfischSalat,
                    huehnerstreifenSalat, nudelsalat, herbstsalat, chillikaeseSnack, dKornweckerlOhneHefeSchafkaese, lachsweckerl,
                    humussnaek, dinkelkaeseweckerlMitEmmentaler, laugenCammenbertweckerl, kornspitzSchinkenweckerl, prosciuttosnack,
                    grahamEiSnack, pharaoweckerlMitTofu, karottenCurrySnack, vitalsnack, heurigensnack, schinkenKaesecroissant,
                    mistschaufelUndBsesen, putzbuersteOval, geschirrtuecher, wettexTuecher, kuechenrollen4er, mikrofasertuecher,
                    frischhaltefolie, bodenMicrofasertuch, teppich80x120, rollenhandtuchBlau, falthandtuchFuerSpaender,
                    abwaschschwaemme, reziTopfreiniger, spuckschutz, spuckschutzfuerVerkostung, einweghandschuheL, einweghandschuheM,
                    plastikhandschuhe, gebaeckszangeLang, gebaeckszangeKurz, ofenhandschuhe, geldsackerlRot, geschenkband,
                    a4PaoierFax, kassarollen, gradwohlEtikettenKl, prospektInternetshop, sammelpaesse, allergieliste,
                    waldhonig450g, waldhonig220g, bluetencremehonig220g, bluetencremehonig450g, lindenbluetenhonig220g, lindenbluetenhonig450g,
                    akazienhonig220g, akazienhonig450g, portionsmarmelade, zwetschkenMarelade, erdbeerMarelade,
                    weichselMarelade, marillenMarelade, waldheidelbeerMarelade, schokoKirschMarelade, becherFrischGebresterSaft,
                    koffeinfreiHaag250g, kaffeeExcelsior1kg, trinkschokoPompadurWMF1kg, klDeckelfKaffeeb, mittelDeckelfKaffeeb,
                    grDeckelfKaffeeb, klKaffeeb, mittlereKaffeeb, geKaffeeb, kaffeebecherhalter2er, kaffeestaebchen,
                    plastikLoeffelGr, plastikGabelGR, plastiMesserGR, strohhalme, zuckerportionen, ruebenzuckerPortionen, bruanerPorionszucker,
                    kandisinPortioniert, tragtaschenNeu, stofftaschen, sackerl1kg, sackerl2kg, sackerl15kg, sackerl35kg,
                    pergaminsackerl, snacksackerlGelocht, baguettesackerl, cellophansackerl, pappteller10x16, pappteller13x20,
                    pappteller16x23, blechpapierGeschnitten, servietten,wcPapier10er, duftsprueherNachfuellung, blueStarEinhaenger,
                    blueStarNachfuellung, pissoirEinleger, wcReiniger1Liter, seifeFuerSpender, klarspuelerGeschirrspueler, salzGeschirrspueler,
                    tabsGeschirrspueler, priBlau, muellsaecke35Liter, muellsaecke110Liter, muellsaecke150Liter, wmfMilchreiniger,
                    wmfRinigungstabletten, wmfEntkalker, ofenProCleaner2in1, holluGlanzreinigerKanister, holluvitDuroKanister,
                    danclorKanister, uNI810Allzwekreiniger1Liter, feinsteinzeugreiniger1Liter, kalkreiniger1Liter, zwergenwiesePaprika,
                    zwergenwieseMeerettich, zwergenwieseBasillikum, zwergenwieseSchnittlauch, sojamilch1Liter, hafermilch1Liter, bleche60x40,
                    dieKraftDerKoerner, krapfenschachtelGr, krapfenschachtelKL, tortenkartonGefaltenGr1, tortenkartonGefaltenGr2, tortenkartonGefaltenGr3,
                    tortenkartonGefaltenGr4, plastiksackerlZumAbreissenMittel, plastiksackerlZumAbreissenGross, plastiksackerlZumAbreissenKlein,
                    geschenkfolieBedruckt, bedrucktesGradwohlpapier, tortenpapierDurchsichtigGeschnitten, tortenspitzenpapier
                    );



            produktRepository.saveAll(produkte);

            WarenbestellungId id1 =
                    WarenbestellungId.builder()
                                    .datum(LocalDate.of(2022, 8, 30))
                                    .filiale(hietzing)
                                    .produkt(baerlauchbrot)
                                    .build();

            Warenbestellung bestellung1 =
                    Warenbestellung.builder()
                                    .id(id1)
                                    .menge(2)
                                    .build();

            WarenbestellungId id2 =
                    WarenbestellungId.builder()
                            .datum(LocalDate.of(2022, 8, 30))
                            .filiale(hietzing)
                            .produkt(dinkelbrotStange)
                            .build();

            Warenbestellung bestellung2 =
                    Warenbestellung.builder()
                            .id(id2)
                            .menge(0.5)
                            .build();

            WarenbestellungId id3 =
                    WarenbestellungId.builder()
                            .datum(LocalDate.of(2022, 8, 30))
                            .filiale(hietzing)
                            .produkt(pharaobriochestriezerl)
                            .build();

            Warenbestellung bestellung3 =
                    Warenbestellung.builder()
                            .id(id3)
                            .menge(3)
                            .build();

            WarenbestellungId id4 =
                    WarenbestellungId.builder()
                            .datum(LocalDate.of(2022, 8, 29))
                            .filiale(mariahilfer)
                            .produkt(pharaocroissant)
                            .build();

            Warenbestellung bestellung4 =
                    Warenbestellung.builder()
                            .id(id4)
                            .menge(5)
                            .build();


            List<Warenbestellung> bestellungen = Arrays.asList(
                    bestellung1, bestellung2, bestellung3, bestellung4
            );

            warenbestellungRepository.saveAll(bestellungen);

            KundenbestellungId kundenbestellungId1 =
                    KundenbestellungId.builder()
                            .datum(LocalDate.of(2023,9,1))
                            .filiale(hietzing)
                            .produkt(nusskipferl)
                            .kunde("Daniel")
                            .build();

            Kundenbestellung kundenbestellung1 =
                    Kundenbestellung.builder()
                            .menge(1)
                            .id(kundenbestellungId1)
                            .build();

            KundenbestellungId kundenbestellungId2 =
                    KundenbestellungId.builder()
                            .datum(LocalDate.of(2023, 9,2))
                            .filiale(hietzing)
                            .produkt(kastanienzipf)
                            .kunde("Herbert")
                            .build();

            Kundenbestellung kundenbestellung2 =
                    Kundenbestellung.builder()
                            .menge(2)
                            .id(kundenbestellungId2)
                            .build();

            List<Kundenbestellung> kundenbestellungen = Arrays.asList(
              kundenbestellung1, kundenbestellung2
            );

            kundenbestellungRepository.saveAll(kundenbestellungen);

            VorlageId vorlageId1 =
                    VorlageId.builder()
                            .id(1)
                            .filiale(hietzing)
                            .produkt(bauernbrot)
                            .build();

            Vorlage vorlage1 =
                    Vorlage.builder()
                            .id(vorlageId1)
                            .menge(2)
                            .build();

            VorlageId vorlageId2 =
                    VorlageId.builder()
                            .id(1)
                            .filiale(hietzing)
                            .produkt(roggenbrot)
                            .build();

            Vorlage vorlage2 =
                    Vorlage.builder()
                            .id(vorlageId2)
                            .menge(1)
                            .build();

            VorlageId vorlageId3 =
                    VorlageId.builder()
                            .id(2)
                            .filiale(mariahilfer)
                            .produkt(bauernbrotStange)
                            .build();

            Vorlage vorlage3 =
                    Vorlage.builder()
                            .id(vorlageId3)
                            .menge(0.5)
                            .build();

            List<Vorlage> vorlagen = Arrays.asList(
              vorlage1, vorlage2,  vorlage3
            );

            vorlageRepository.saveAll(vorlagen);

            LieferbarId lieferbarId1 =
                    LieferbarId.builder()
                            .firma(wien)
                            .produkt(topfengolatsche)
                            .build();


            Lieferbar lieferbar1 =
                    Lieferbar.builder()
                            .id(lieferbarId1)
                            .montag(true)
                            .dienstag(true)
                            .mittwoch(true)
                            .donnerstag(true)
                            .freitag(true)
                            .samstag(false)
                            .sonntag(false)
                            .build();

            LieferbarId lieferbarId2 =
                    LieferbarId.builder()
                            .firma(wien)
                            .produkt(marillenKrapfen)
                            .build();


            Lieferbar lieferbar2 =
                    Lieferbar.builder()
                            .id(lieferbarId2)
                            .montag(true)
                            .dienstag(true)
                            .mittwoch(true)
                            .donnerstag(true)
                            .freitag(true)
                            .samstag(false)
                            .sonntag(false)
                            .build();

            LieferbarId lieferbarId3 =
                    LieferbarId.builder()
                            .firma(teigwerkstatt)
                            .produkt(bauernbrot)
                            .build();


            Lieferbar lieferbar3 =
                    Lieferbar.builder()
                            .id(lieferbarId3)
                            .montag(true)
                            .dienstag(true)
                            .mittwoch(true)
                            .donnerstag(true)
                            .freitag(true)
                            .samstag(false)
                            .sonntag(false)
                            .build();

            List<Lieferbar> lieferbar = Arrays.asList(
                    lieferbar1, lieferbar2, lieferbar3
            );

            lieferbarRepository.saveAll(lieferbar);

            DienstplanId dienstplanId1 =
                    DienstplanId.builder()
                            .mitarbeiter(szimone)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 8, 1))
                            .build();

            Dienstplan dienstplan1 =
                    Dienstplan.builder()
                            .id(dienstplanId1)
                            .bis(LocalTime.of(11,0))
                            .build();

            DienstplanId dienstplanId2 =
                    DienstplanId.builder()
                            .mitarbeiter(barbara)
                            .filiale(hietzing)
                            .von(LocalTime.of(11, 0))
                            .datum(LocalDate.of(2023, 8, 1))
                            .build();

            Dienstplan dienstplan2 =
                    Dienstplan.builder()
                            .id(dienstplanId2)
                            .bis(LocalTime.of(19,0))
                            .build();

            DienstplanId dienstplanId15 =
                    DienstplanId.builder()
                            .mitarbeiter(maybrit)
                            .filiale(hietzing)
                            .von(LocalTime.of(11, 0))
                            .datum(LocalDate.of(2023, 8, 1))
                            .build();

            Dienstplan dienstplan15 =
                    Dienstplan.builder()
                            .id(dienstplanId15)
                            .bis(LocalTime.of(19,0))
                            .build();

            DienstplanId dienstplanId3 =
                    DienstplanId.builder()
                            .mitarbeiter(szimone)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 8, 2))
                            .build();

            Dienstplan dienstplan3 =
                    Dienstplan.builder()
                            .id(dienstplanId3)
                            .bis(LocalTime.of(11,0))
                            .build();

            DienstplanId dienstplanId4 =
                    DienstplanId.builder()
                            .mitarbeiter(barbara)
                            .filiale(hietzing)
                            .von(LocalTime.of(11, 0))
                            .datum(LocalDate.of(2023, 8, 2))
                            .build();

            Dienstplan dienstplan4 =
                    Dienstplan.builder()
                            .id(dienstplanId4)
                            .bis(LocalTime.of(19,0))
                            .build();

            DienstplanId dienstplanId5 =
                    DienstplanId.builder()
                            .mitarbeiter(szimone)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 8, 3))
                            .build();

            Dienstplan dienstplan5 =
                    Dienstplan.builder()
                            .id(dienstplanId5)
                            .bis(LocalTime.of(11,0))
                            .build();

            DienstplanId dienstplanId6 =
                    DienstplanId.builder()
                            .mitarbeiter(barbara)
                            .filiale(hietzing)
                            .von(LocalTime.of(11, 0))
                            .datum(LocalDate.of(2023, 8, 3))
                            .build();

            Dienstplan dienstplan6 =
                    Dienstplan.builder()
                            .id(dienstplanId6)
                            .bis(LocalTime.of(19,0))
                            .build();

            DienstplanId dienstplanId7 =
                    DienstplanId.builder()
                            .mitarbeiter(szimone)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 8, 4))
                            .build();

            Dienstplan dienstplan7 =
                    Dienstplan.builder()
                            .id(dienstplanId7)
                            .bis(LocalTime.of(11,0))
                            .build();

            DienstplanId dienstplanId8 =
                    DienstplanId.builder()
                            .mitarbeiter(barbara)
                            .filiale(hietzing)
                            .von(LocalTime.of(11, 0))
                            .datum(LocalDate.of(2023, 8, 4))
                            .build();

            Dienstplan dienstplan8 =
                    Dienstplan.builder()
                            .id(dienstplanId8)
                            .bis(LocalTime.of(19,0))
                            .build();

            DienstplanId dienstplanId9 =
                    DienstplanId.builder()
                            .mitarbeiter(barbara)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 8, 5))
                            .build();

            Dienstplan dienstplan9 =
                    Dienstplan.builder()
                            .id(dienstplanId9)
                            .bis(LocalTime.of(13,0))
                            .build();

            DienstplanId dienstplanId10 =
                    DienstplanId.builder()
                            .mitarbeiter(barbara)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 8, 7))
                            .build();

            Dienstplan dienstplan10 =
                    Dienstplan.builder()
                            .id(dienstplanId10)
                            .bis(LocalTime.of(13,0))
                            .build();

            DienstplanId dienstplanId11 =
                    DienstplanId.builder()
                            .mitarbeiter(szimone)
                            .filiale(hietzing)
                            .von(LocalTime.of(13, 0))
                            .datum(LocalDate.of(2023, 8, 7))
                            .build();

            Dienstplan dienstplan11 =
                    Dienstplan.builder()
                            .id(dienstplanId11)
                            .bis(LocalTime.of(19,0))
                            .build();

            DienstplanId dienstplanId12 =
                    DienstplanId.builder()
                            .mitarbeiter(szimone)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2022, 12, 24))
                            .build();

            Dienstplan dienstplan12 =
                    Dienstplan.builder()
                            .id(dienstplanId12)
                            .bis(LocalTime.of(13,0))
                            .build();

            DienstplanId dienstplanId13 =
                    DienstplanId.builder()
                            .mitarbeiter(szimone)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 10, 7))
                            .build();

            Dienstplan dienstplan13 =
                    Dienstplan.builder()
                            .id(dienstplanId13)
                            .bis(LocalTime.of(13,0))
                            .build();

            DienstplanId dienstplanId14 =
                    DienstplanId.builder()
                            .mitarbeiter(andrea)
                            .filiale(hietzing)
                            .von(LocalTime.of(5, 30))
                            .datum(LocalDate.of(2023, 10, 9))
                            .build();

            Dienstplan dienstplan14 =
                    Dienstplan.builder()
                            .id(dienstplanId14)
                            .bis(LocalTime.of(13,0))
                            .build();

            DienstplanId dienstplanId16 =
                    DienstplanId.builder()
                            .mitarbeiter(barbara)
                            .filiale(hietzing)
                            .von(LocalTime.of(13, 00))
                            .datum(LocalDate.of(2023, 10, 10))
                            .build();

            Dienstplan dienstplan16 =
                    Dienstplan.builder()
                            .id(dienstplanId16)
                            .bis(LocalTime.of(19,0))
                            .build();

            List<Dienstplan> dienstplans = Arrays.asList(
                    dienstplan1, dienstplan2, dienstplan3, dienstplan4, dienstplan5, dienstplan6, dienstplan7,
                    dienstplan8, dienstplan9, dienstplan10, dienstplan11, dienstplan12, dienstplan13, dienstplan14,
                    dienstplan15, dienstplan16
            );

            dienstplanRepo.saveAll(dienstplans);

            Nachricht nachricht1 =
                    Nachricht.builder()
                            .nachricht("Das ist eine Testnachricht")
                            .datum(LocalDate.of(2023, 9, 24))
                            .build();

            Nachricht nachricht2 =
                    Nachricht.builder()
                            .nachricht("Andere Nachrich \n Test")
                            .datum(LocalDate.of(2023, 9, 25))
                            .build();

            List<Nachricht> nachrichten = Arrays.asList(
              nachricht1, nachricht2
            );

            nachrichtRepository.saveAll(nachrichten);

            NachrichtSendenId nachrichtSendenId1 =
                    NachrichtSendenId.builder()
                            .filiale(hietzing)
                            .nachricht(nachricht1)
                            .build();

            NachrichtSendenId nachrichtSendenId2 =
                    NachrichtSendenId.builder()
                            .filiale(mariahilfer)
                            .nachricht(nachricht2)
                            .build();

            NachrichtSendenId nachrichtSendenId3 =
                    NachrichtSendenId.builder()
                            .filiale(hietzing)
                            .nachricht(nachricht2)
                            .build();

            NachrichtSenden nachrichtSenden1 =
                    NachrichtSenden.builder()
                            .id(nachrichtSendenId1)
                            .build();

            NachrichtSenden nachrichtSenden2 =
                    NachrichtSenden.builder()
                            .id(nachrichtSendenId2)
                            .build();

            NachrichtSenden nachrichtSenden3 =
                    NachrichtSenden.builder()
                            .id(nachrichtSendenId3)
                            .build();

            List<NachrichtSenden> nachrichtenSenden = Arrays.asList(
                    nachrichtSenden1, nachrichtSenden2, nachrichtSenden3
            );

            nachrichtSendenRepository.saveAll(nachrichtenSenden);


            Urlaubstage urlaubstage1 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 1, 1))
                    .name("Neujahr")
                    .build();

            Urlaubstage urlaubstage2 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 1, 6))
                    .name("Heilige Drei Könige")
                    .build();

            Urlaubstage urlaubstage3 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 5, 1))
                    .name("Tag der Arbeit")
                    .build();

            Urlaubstage urlaubstage4 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 8, 15))
                    .name("Maria Himmelfahrt")
                    .build();

            Urlaubstage urlaubstage5 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 10, 26))
                    .name("Nationalfeiertag")
                    .build();

            Urlaubstage urlaubstage6 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 11, 1))
                    .name("Allerheiligen")
                    .build();

            Urlaubstage urlaubstage7 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 12, 8))
                    .name("Mariä Empfängnis")
                    .build();

            Urlaubstage urlaubstage8 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 12, 25))
                    .name("Weihnachten")
                    .build();

            Urlaubstage urlaubstage9 = Urlaubstage.builder()
                    .datum(LocalDate.of(2023, 12, 26))
                    .name("Stephanstag")
                    .build();


            List<Urlaubstage> urlaubstage = Arrays.asList(
                    urlaubstage1, urlaubstage2, urlaubstage3, urlaubstage4,
                    urlaubstage5, urlaubstage6, urlaubstage7, urlaubstage8, urlaubstage9
            );

            urlaubgstageRepository.saveAll(urlaubstage);


            FirmenUrlaubId firmenUrlaubBurgenland1id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage1)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland1 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland1id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland2id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage2)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland2 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland2id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland3id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage3)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland3 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland3id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland4id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage4)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland4 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland4id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland5id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage5)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland5 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland5id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland6id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage6)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland6 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland6id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland7id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage7)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland7 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland7id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland8id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage8)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland8 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland8id)
                    .build();

            FirmenUrlaubId firmenUrlaubBurgenland9id = FirmenUrlaubId.builder()
                    .firma(burgenland)
                    .urlaubstage(urlaubstage9)
                    .build();

            FirmenUrlaub firmenUrlaubBurgenland9 = FirmenUrlaub.builder()
                    .id(firmenUrlaubBurgenland9id)
                    .build();

            List<FirmenUrlaub> urlaubstageList = Arrays.asList(
                    firmenUrlaubBurgenland1, firmenUrlaubBurgenland2, firmenUrlaubBurgenland3, firmenUrlaubBurgenland4,
                    firmenUrlaubBurgenland5, firmenUrlaubBurgenland6, firmenUrlaubBurgenland7, firmenUrlaubBurgenland8,
                    firmenUrlaubBurgenland9
            );

            firmenUrlaubRepository.saveAll(urlaubstageList);
        };
    }
}
