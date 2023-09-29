import { firma } from "../firma/firma";
import { produkt } from "../produkt/produkt";

export interface lieferbarId{
    produkt: produkt,
    firma: firma
}