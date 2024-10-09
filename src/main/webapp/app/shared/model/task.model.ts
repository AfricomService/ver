import { Moment } from 'moment';
import { IComment } from 'app/shared/model/comment.model';
import { TaskStatus } from 'app/shared/model/enumerations/task-status.model';

export interface ITask {
  id?: number;
  taskId?: number;
  name?: string;
  description?: string;
  startDate?: Moment;
  endDate?: Moment;
  estimatedCost?: number;
  status?: TaskStatus;
  stakeholderId?: number;
  comments?: IComment[];
  projectId?: number;
  resourceId?: number;
}

export class Task implements ITask {
  constructor(
    public id?: number,
    public taskId?: number,
    public name?: string,
    public description?: string,
    public startDate?: Moment,
    public endDate?: Moment,
    public estimatedCost?: number,
    public status?: TaskStatus,
    public stakeholderId?: number,
    public comments?: IComment[],
    public projectId?: number,
    public resourceId?: number
  ) {}
}
