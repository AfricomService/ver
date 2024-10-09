import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IResource, Resource } from 'app/shared/model/resource.model';
import { ResourceService } from './resource.service';

@Component({
  selector: 'jhi-resource-update',
  templateUrl: './resource-update.component.html',
})
export class ResourceUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    resourceId: [null, [Validators.required]],
    name: [null, [Validators.required]],
    role: [null, [Validators.required]],
    hourlyCost: [null, [Validators.required]],
  });

  constructor(protected resourceService: ResourceService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ resource }) => {
      this.updateForm(resource);
    });
  }

  updateForm(resource: IResource): void {
    this.editForm.patchValue({
      id: resource.id,
      resourceId: resource.resourceId,
      name: resource.name,
      role: resource.role,
      hourlyCost: resource.hourlyCost,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const resource = this.createFromForm();
    if (resource.id !== undefined) {
      this.subscribeToSaveResponse(this.resourceService.update(resource));
    } else {
      this.subscribeToSaveResponse(this.resourceService.create(resource));
    }
  }

  private createFromForm(): IResource {
    return {
      ...new Resource(),
      id: this.editForm.get(['id'])!.value,
      resourceId: this.editForm.get(['resourceId'])!.value,
      name: this.editForm.get(['name'])!.value,
      role: this.editForm.get(['role'])!.value,
      hourlyCost: this.editForm.get(['hourlyCost'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IResource>>): void {
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
}
