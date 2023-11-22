import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { kundenbestellung } from 'src/model/kundenbestellung/kundenbestellung';

@Injectable({
  providedIn: 'root'
})
export class KundenbestellungService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getAllKundenbestellungen(){
    return this.http.get(this.baseUri + '/kundenbestellung')
  }

  getKundenbestellungByFiliale(filialeId: number){
    return this.http.get(this.baseUri+'/kundenbestellung/filiale/'+filialeId)
  }

  getKundenbestellungByDate(datum: string){
    return this.http.get(this.baseUri+'/kundenbestellung/date/'+datum)
  }

  insertKundenbestellung(kundenbestellungToInsert: kundenbestellung){
    return this.http.post(this.baseUri+'/kundenbestellung', kundenbestellungToInsert)
  }

  updateKundenbestellung(datum: string, 
                        produktId: number, 
                        filialeId: number, 
                        kunde: string, 
                        kundenbestellungToInsert: kundenbestellung){
    return this.http.put(this.baseUri+'/kundenbestellung/'+datum+'/'+produktId+'/'+filialeId+'/'+kunde, kundenbestellungToInsert)
  }

  deleteKundenbestellung(datum: string, 
    produktId: number, 
    filialeId: number, 
    kunde: string){
    return this.http.delete(this.baseUri+'/kundenbestellung/'+datum+'/'+produktId+'/'+filialeId+'/'+kunde)
  }
}
