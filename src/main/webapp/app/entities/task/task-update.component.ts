import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { ITask, Task } from 'app/shared/model/task.model';
import { TaskService } from './task.service';
import { IStakeholder } from 'app/shared/model/stakeholder.model';
import { StakeholderService } from 'app/entities/stakeholder/stakeholder.service';
import { IProject } from 'app/shared/model/project.model';
import { ProjectService } from 'app/entities/project/project.service';
import { IResource } from 'app/shared/model/resource.model';
import { ResourceService } from 'app/entities/resource/resource.service';

type SelectableEntity = IStakeholder | IProject | IResource;

@Component({
  selector: 'jhi-task-update',
  templateUrl: './task-update.component.html',
})
export class TaskUpdateComponent implements OnInit {
  isSaving = false;
  stakeholders: IStakeholder[] = [];
  projects: IProject[] = [];
  resources: IResource[] = [];
  startDateDp: any;
  endDateDp: any;

  editForm = this.fb.group({
    id: [],
    taskId: [null, [Validators.required]],
    name: [null, [Validators.required]],
    description: [],
    startDate: [null, [Validators.required]],
    endDate: [],
    estimatedCost: [],
    status: [null, [Validators.required]],
    stakeholderId: [],
    projectId: [],
    resourceId: [],
  });

  constructor(
    protected taskService: TaskService,
    protected stakeholderService: StakeholderService,
    protected projectService: ProjectService,
    protected resourceService: ResourceService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ task }) => {
      this.updateForm(task);

      this.stakeholderService
        .query({ filter: 'task-is-null' })
        .pipe(
          map((res: HttpResponse<IStakeholder[]>) => {
            return res.body || [];
          })
        )
        .subscribe((resBody: IStakeholder[]) => {
          if (!task.stakeholderId) {
            this.stakeholders = resBody;
          } else {
            this.stakeholderService
              .find(task.stakeholderId)
              .pipe(
                map((subRes: HttpResponse<IStakeholder>) => {
                  return subRes.body ? [subRes.body].concat(resBody) : resBody;
                })
              )
              .subscribe((concatRes: IStakeholder[]) => (this.stakeholders = concatRes));
          }
        });

      this.projectService.query().subscribe((res: HttpResponse<IProject[]>) => (this.projects = res.body || []));

      this.resourceService.query().subscribe((res: HttpResponse<IResource[]>) => (this.resources = res.body || []));
    });
  }

  updateForm(task: ITask): void {
    this.editForm.patchValue({
      id: task.id,
      taskId: task.taskId,
      name: task.name,
      description: task.description,
      startDate: task.startDate,
      endDate: task.endDate,
      estimatedCost: task.estimatedCost,
      status: task.status,
      stakeholderId: task.stakeholderId,
      projectId: task.projectId,
      resourceId: task.resourceId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const task = this.createFromForm();
    if (task.id !== undefined) {
      this.subscribeToSaveResponse(this.taskService.update(task));
    } else {
      this.subscribeToSaveResponse(this.taskService.create(task));
    }
  }

  private createFromForm(): ITask {
    return {
      ...new Task(),
      id: this.editForm.get(['id'])!.value,
      taskId: this.editForm.get(['taskId'])!.value,
      name: this.editForm.get(['name'])!.value,
      description: this.editForm.get(['description'])!.value,
      startDate: this.editForm.get(['startDate'])!.value,
      endDate: this.editForm.get(['endDate'])!.value,
      estimatedCost: this.editForm.get(['estimatedCost'])!.value,
      status: this.editForm.get(['status'])!.value,
      stakeholderId: this.editForm.get(['stakeholderId'])!.value,
      projectId: this.editForm.get(['projectId'])!.value,
      resourceId: this.editForm.get(['resourceId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ITask>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: SelectableEntity): any {
    return item.id;
  }
}
