import { lieferbarId } from "./lieferbarId"

export interface lieferbar{
    id: lieferbarId,
    montag: boolean,
    dienstag: boolean,
    mittwoch: boolean,
    donnerstag: boolean,
    freitag: boolean,
    samstag: boolean,
    sonntag: boolean
}