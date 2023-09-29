import { filiale } from "../filiale/filiale";
import { produkt } from "../produkt/produkt";

export interface vorlageId{
    id: number,
    produkt: produkt,
    filiale: filiale
}