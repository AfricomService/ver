import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IStakeholder } from 'app/shared/model/stakeholder.model';

type EntityResponseType = HttpResponse<IStakeholder>;
type EntityArrayResponseType = HttpResponse<IStakeholder[]>;

@Injectable({ providedIn: 'root' })
export class StakeholderService {
  public resourceUrl = SERVER_API_URL + 'api/stakeholders';

  constructor(protected http: HttpClient) {}

  create(stakeholder: IStakeholder): Observable<EntityResponseType> {
    return this.http.post<IStakeholder>(this.resourceUrl, stakeholder, { observe: 'response' });
  }

  update(stakeholder: IStakeholder): Observable<EntityResponseType> {
    return this.http.put<IStakeholder>(this.resourceUrl, stakeholder, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IStakeholder>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IStakeholder[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
