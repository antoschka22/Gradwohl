import { filiale } from "../filiale/filiale"
import { produkt } from "../produkt/produkt"

export interface warenbestellungID{
    datum: Date | string,
    produkt: produkt,
    filiale: filiale
}