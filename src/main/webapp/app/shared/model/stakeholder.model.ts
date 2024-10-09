export interface IStakeholder {
  id?: number;
  stakeholderId?: number;
  name?: string;
  role?: string;
  taskId?: number;
}

export class Stakeholder implements IStakeholder {
  constructor(public id?: number, public stakeholderId?: number, public name?: string, public role?: string, public taskId?: number) {}
}
