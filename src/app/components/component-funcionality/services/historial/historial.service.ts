import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class HistorialService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${environment.token}`
    })};

  private urlHistorial = `${environment.apiUrlApiGateWay}/api/transactionalDataSimple`;
  private urlAttendance = `${environment.apiUrlApiGateWay}/v1/attendance`;
  private urlPrograms = `${environment.apiUrlApiGateWay}/v1/programs`;
  private urlActivities = `${environment.apiUrlApiGateWay}/ms-soa`;
  private urlFuncionary = `${environment.apiUrlApiGateWay}/api/funcionaryData`;
  private urlTeenager = `${environment.apiUrlApiGateWay}/api/teenData`;
  constructor(private _http: HttpClient) { }



  findAll() {
    return this._http.get(`${this.urlHistorial}/listData`);
  }

  findAllDataActive() {
    return this._http.get(this.urlHistorial + '/listData');
  }

  findAllAttendance() {
    return this._http.get(`${this.urlAttendance}`);
  }

  findAllActivities() {
    return this._http.get(`${this.urlActivities}/listData`,this.httpOptions);
  }
  findAllPrograms() {
    return this._http.get(`${this.urlPrograms}/list`);
  }
  findAllFuncionary() {
    return this._http.get(`${this.urlFuncionary}/listData`);
  }
  findAllTennager() {
    return this._http.get(`${this.urlTeenager}/listData`);
  }
  getActivitiesDataById(id: number) {
    return this._http.get(`${this.urlActivities}/${id}`);
  }
}
