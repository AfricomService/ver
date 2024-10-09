import { Moment } from 'moment';
import { ITask } from 'app/shared/model/task.model';
import { ProjectStatus } from 'app/shared/model/enumerations/project-status.model';

export interface IProject {
  id?: number;
  projectId?: number;
  name?: string;
  description?: string;
  status?: ProjectStatus;
  startDate?: Moment;
  totalCost?: number;
  tasks?: ITask[];
}

export class Project implements IProject {
  constructor(
    public id?: number,
    public projectId?: number,
    public name?: string,
    public description?: string,
    public status?: ProjectStatus,
    public startDate?: Moment,
    public totalCost?: number,
    public tasks?: ITask[]
  ) {}
}
