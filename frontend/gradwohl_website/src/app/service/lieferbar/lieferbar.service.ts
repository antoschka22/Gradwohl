import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { lieferbar } from 'src/model/lieferbar/lieferbar';

@Injectable({
  providedIn: 'root'
})
export class LieferbarService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  
  getLieferbarByFirma(firma: string){
    return this.http.get(this.baseUri + '/lieferbar/'+firma)
  }            

  insertLieferbar(lieferbarToInsert: lieferbar[]){
    return this.http.post(this.baseUri+'/lieferbar', lieferbarToInsert)
  }

  deleteLieferbar(firmaId: string, produktId: number){
    return this.http.delete(this.baseUri+'/lieferbar/'+firmaId+'/'+produktId)
  }

  updateLieferbar(firmaId: string, produktId: number, liefebarToUpdate: lieferbar){
    return this.http.put(this.baseUri+'/lieferbar/'+firmaId+'/'+produktId, liefebarToUpdate)
  }
}
