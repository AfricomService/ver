import { RiskStatus } from 'app/shared/model/enumerations/risk-status.model';

export interface IRisk {
  id?: number;
  riskId?: number;
  description?: string;
  probability?: number;
  impact?: number;
  status?: RiskStatus;
}

export class Risk implements IRisk {
  constructor(
    public id?: number,
    public riskId?: number,
    public description?: string,
    public probability?: number,
    public impact?: number,
    public status?: RiskStatus
  ) {}
}
