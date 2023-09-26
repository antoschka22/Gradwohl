import { warenbestellungID } from "./warenbestellungID"

export interface warenbestellung{
    id: warenbestellungID,
    menge: number
}

// {
//     "id": {
//       "datum": "2022-08-30",
//       "produkt": {
//         "id": 754,
//         "name": "Bärlauchbrot",
//         "produktgruppe": {
//           "name": "VK Brote"
//         },
//         "bio": false,
//         "mehl": "Dinkel",
//         "hb": false
//       },
//       "filiale": {
//         "id": 14,
//         "name": "Hietzing",
//         "firma": {
//           "name": "Wien"
//         }
//       }
//     },
//     "menge": 2
//   }