import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {environment} from "../../../../../environments/environment.development";
import {Activities} from "../../models/activities/activities.model";

@Injectable({
  providedIn: 'root'
})
export class ActivitiesService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${environment.token}`
    })};

  private urlActivities = `${environment.apiUrlApiGateWay}/ms-soa`;
  funcionarySelected: Activities | undefined = undefined;
  constructor(private _http: HttpClient) { }

  findAll() {
    return this._http.get(`${this.urlActivities}/listData`, this.httpOptions);
  }

  findAllDataActive() {
    return this._http.get(this.urlActivities + '/listData/active', this.httpOptions);
  }

  findAllDataInactive() {
    return this._http.get(this.urlActivities + '/listData/inactive', this.httpOptions);
  }

  deleteLogicalDataTeen(activities: Activities) {
    return this._http.delete(
      this.urlActivities + '/deleteLogical/' + activities.id,
      this.httpOptions
    );
  }

  reactiveLogicalDataTeen(activities: Activities) {
    return this._http.put(
      this.urlActivities + '/reactiveLogical/' + activities.id,
      activities, this.httpOptions
    );
  }

  saveNewFuncionary(activities: Activities) {
    return this._http.post(this.urlActivities+ '/save', activities, this.httpOptions);
  }

  updateDataFuncionary(activities: Activities) {
    return this._http.put(`${this.urlActivities}/update/${activities.id}`, activities, this.httpOptions);

  }
  findDataRankLegalGuardian() {
    return this._http.get(this.urlActivities + '/listData/active', this.httpOptions);
  }
}


