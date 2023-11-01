import { filiale } from "../filiale/filiale"
import { role } from "../role/role"

export interface mitabeiter{
    id: number,
    name: string,
    password: string,
    role: role,
    filiale: filiale,
    springer: boolean
}