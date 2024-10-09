import { ITask } from 'app/shared/model/task.model';

export interface IResource {
  id?: number;
  resourceId?: number;
  name?: string;
  role?: string;
  hourlyCost?: number;
  tasks?: ITask[];
}

export class Resource implements IResource {
  constructor(
    public id?: number,
    public resourceId?: number,
    public name?: string,
    public role?: string,
    public hourlyCost?: number,
    public tasks?: ITask[]
  ) {}
}
