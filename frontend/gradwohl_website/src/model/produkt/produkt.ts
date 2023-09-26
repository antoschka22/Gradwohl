import { produktgruppe } from "../produktgruppe/produktgruppe"

export interface produkt{
    id: number,
    name: string,
    produktgruppe: produktgruppe,
    bio: boolean,
    mehl: string,
    hb: boolean
}