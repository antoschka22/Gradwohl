import { firma } from "../firma/firma"

export interface filiale{
    id: number,
    name: string,
    firma: firma,
    soOffen: boolean
}