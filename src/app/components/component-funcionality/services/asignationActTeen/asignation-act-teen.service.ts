import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment.development';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {AsignationActTeen} from "../../models/asignationActTeen/asignationActTeen";
import {AsignationActTeenComplete} from "../../models/asignationActTeen/asignationActTeenComplete";

@Injectable({
  providedIn: 'root'
})
export class AsignationActTeenService {

  private urlAsignation = `${environment.apiUrlApiGateWay}/api/transaccionalData`;
  asignationSelected: AsignationActTeen | undefined = undefined;
  transactionSelected: AsignationActTeenComplete | undefined = undefined;
  httpOption={
    headers:new HttpHeaders({
      'Content-Type':'aplication/json',
      'Authorization':`Bearer ${environment.token}`
    })
  };
  constructor(private _http: HttpClient) {}

  findAll() {
    return this._http.get(this.urlAsignation + '/listData',this.httpOption);
  }

  findAllDatosWithoutBody() {
    return this._http.get(this.urlAsignation + '/listDataIdRegister',this.httpOption);
  }

  findAllDataActive() {
    return this._http.get(this.urlAsignation + '/listData/active',this.httpOption);
  }

  findDataIdRegister() {
    return this._http.get(this.urlAsignation + '/listDataIdRegister');
  }

  findDataTeenNoRegistered() {
    return this._http.get(this.urlAsignation + '/listData/active');
  }

  findAllDataInactive() {
    return this._http.get(this.urlAsignation + '/listData/inactive');
  }

  saveNewAsignation(asignation: AsignationActTeen) {
    return this._http.post(this.urlAsignation, asignation);
  }

  updateDataAsignation(asignation: AsignationActTeen) {
    return this._http.put(
      this.urlAsignation + '/' + asignation.id_activities,
      asignation
    );
  }

  updateTwoWayAsignation(twoWayAsignation: AsignationActTeenComplete) {
    return this._http.put(
      this.urlAsignation +
      '/' +
      twoWayAsignation.transaccionalActTeen.id_teenager,
      twoWayAsignation
    );
  }

  deleteLogicalDataAsignation(asignation: AsignationActTeen) {
    return this._http.patch(
      this.urlAsignation + '/deleteLogical/' + asignation.id_activities,
      asignation
    );
  }

  reactiveLogicalDataAsignation(asignation: AsignationActTeen) {
    return this._http.patch(
      this.urlAsignation + '/reactiveLogical/' + asignation.id_activities,
      asignation
    );
  }

  deleteDataAsignationComplete(asignation: AsignationActTeen) {
    return this._http.delete(
      this.urlAsignation + '/' + asignation.id_activities,
    );
  }

  saveMasive(dto: any): Observable<void> {
    return this._http.post<void>(`${this.urlAsignation}/savemasivteen`, dto);
  }
  saveMasiveActivities(dto: any): Observable<void> {
    return this._http.post<void>(`${this.urlAsignation}/savet`, dto);
  }
  exportAsignationReport(dniteen: string): Observable<ArrayBuffer> {
    const url = `${this.urlAsignation}/report/${dniteen}`;
    return this._http.get(url, { responseType: 'arraybuffer' });
  }
}
