import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IIncident } from 'app/shared/model/incident.model';

type EntityResponseType = HttpResponse<IIncident>;
type EntityArrayResponseType = HttpResponse<IIncident[]>;

@Injectable({ providedIn: 'root' })
export class IncidentService {
  public resourceUrl = SERVER_API_URL + 'api/incidents';

  constructor(protected http: HttpClient) {}

  create(incident: IIncident): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(incident);
    return this.http
      .post<IIncident>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(incident: IIncident): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(incident);
    return this.http
      .put<IIncident>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IIncident>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IIncident[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(incident: IIncident): IIncident {
    const copy: IIncident = Object.assign({}, incident, {
      discoveryDate: incident.discoveryDate && incident.discoveryDate.isValid() ? incident.discoveryDate.format(DATE_FORMAT) : undefined,
      mitigationDate:
        incident.mitigationDate && incident.mitigationDate.isValid() ? incident.mitigationDate.format(DATE_FORMAT) : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.discoveryDate = res.body.discoveryDate ? moment(res.body.discoveryDate) : undefined;
      res.body.mitigationDate = res.body.mitigationDate ? moment(res.body.mitigationDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((incident: IIncident) => {
        incident.discoveryDate = incident.discoveryDate ? moment(incident.discoveryDate) : undefined;
        incident.mitigationDate = incident.mitigationDate ? moment(incident.mitigationDate) : undefined;
      });
    }
    return res;
  }
}
