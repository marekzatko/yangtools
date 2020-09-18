/*
 * Copyright (c) 2018, 2020 PANTHEON.tech, s.r.o. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.rfc6643.model.api;

import com.google.common.annotations.Beta;
import org.opendaylight.yangtools.yang.model.api.UnknownSchemaNode;

@Beta
public interface DefValSchemaNode extends UnknownSchemaNode {

    String getArgument();
}
