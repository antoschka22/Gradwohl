import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Globals } from 'src/global/global';
import { firmenUrlaub } from 'src/model/firmenUrlaub/firmenUrlaub';

@Injectable({
  providedIn: 'root'
})
export class FirmenurlaubService {

  private baseUri: string = this.global.backendUri;

  constructor(private http: HttpClient,
              private global: Globals) { }

  getfirmenUrlaubByFirma(firmaId: string){
    return this.http.get(this.baseUri + '/urlaubstage/firma/'+firmaId)
  }

  insertFirmenUrlaub(firmenUrlaub: firmenUrlaub){
    return this.http.post(this.baseUri + '/urlaubstage/firmenUrlaub', firmenUrlaub)
  }

  updateFirmenUrlaub(firmaId: string, urlaubstageId: number, firmenUrlaub: firmenUrlaub){
    return this.http.post(this.baseUri + '/urlaubstage/'+firmaId+'/'+urlaubstageId, firmenUrlaub)
  }

  deleteFirmenUrlaub(firmaId: string, urlaubstageId: number){
    return this.http.delete(this.baseUri + '/urlaubstage/'+firmaId+'/'+urlaubstageId)
  }
}
