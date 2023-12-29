import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { warenbestellung } from 'src/model/warenbestellung/warenbestellung';

@Injectable({
  providedIn: 'root'
})
export class WarenbestellungService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getAllWarenbestellung(){
    return this.http.get(this.baseUri + '/warenbestellung')
  }

  getWarenbestellungByFiliale(filialeId: number){
    return this.http.get(this.baseUri + '/warenbestellung/filiale/'+filialeId)
  }

  getWarenbestellungByDate(datum: string){
    return this.http.get(this.baseUri + '/warenbestellung/date/'+datum)
  }

  updateWarenbestellung(datum: string, produktId: number, filialeId: number, warenbestellungToUpdate: warenbestellung){
    return this.http.put(this.baseUri + '/warenbestellung/'+datum+'/'+produktId+'/'+filialeId, warenbestellungToUpdate);
  }

  deleteWarenbestellung(datum: string, produktId: number, filialeId: number){
    return this.http.delete(this.baseUri+'/warenbestellung/'+datum+'/'+produktId+'/'+filialeId)
  }

  createWarenbestellung(warenbestellungToCreate: warenbestellung[]){
    return this.http.post(this.baseUri+'/warenbestellung', warenbestellungToCreate)
  }
}
