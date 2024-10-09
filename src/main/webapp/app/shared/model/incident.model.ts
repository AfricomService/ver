import { Moment } from 'moment';
import { IncidentSeverity } from 'app/shared/model/enumerations/incident-severity.model';

export interface IIncident {
  id?: number;
  incidentId?: number;
  description?: string;
  discoveryDate?: Moment;
  severity?: IncidentSeverity;
  status?: string;
  responsible?: string;
  mitigationDate?: Moment;
}

export class Incident implements IIncident {
  constructor(
    public id?: number,
    public incidentId?: number,
    public description?: string,
    public discoveryDate?: Moment,
    public severity?: IncidentSeverity,
    public status?: string,
    public responsible?: string,
    public mitigationDate?: Moment
  ) {}
}
