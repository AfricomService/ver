import { Moment } from 'moment';

export interface IComment {
  id?: number;
  commentId?: number;
  content?: string;
  author?: string;
  date?: Moment;
  taskId?: number;
}

export class Comment implements IComment {
  constructor(
    public id?: number,
    public commentId?: number,
    public content?: string,
    public author?: string,
    public date?: Moment,
    public taskId?: number
  ) {}
}
