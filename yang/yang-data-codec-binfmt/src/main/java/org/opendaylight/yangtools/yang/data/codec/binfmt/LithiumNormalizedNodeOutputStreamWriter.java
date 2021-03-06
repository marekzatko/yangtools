/*
 * Copyright (c) 2014, 2015 Cisco Systems, Inc. and others.  All rights reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 */
package org.opendaylight.yangtools.yang.data.codec.binfmt;

import java.io.DataOutput;
import java.io.IOException;
import org.opendaylight.yangtools.yang.common.QName;
import org.opendaylight.yangtools.yang.common.QNameModule;
import org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.AugmentationIdentifier;

/**
 * NormalizedNodeOutputStreamWriter will be used by distributed datastore to send normalized node in
 * a stream.
 * A stream writer wrapper around this class will write node objects to stream in recursive manner.
 * for example - If you have a ContainerNode which has a two LeafNode as children, then
 * you will first call
 * {@link #startContainerNode(org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.NodeIdentifier, int)},
 * then will call
 * {@link #leafNode(org.opendaylight.yangtools.yang.data.api.YangInstanceIdentifier.NodeIdentifier, Object)} twice
 * and then, {@link #endNode()} to end container node.
 *
 * <p>Based on the each node, the node type is also written to the stream, that helps in reconstructing the object,
 * while reading.
 */
final class LithiumNormalizedNodeOutputStreamWriter extends AbstractLithiumDataOutput {
    LithiumNormalizedNodeOutputStreamWriter(final DataOutput output) {
        super(output);
    }

    @Override
    short streamVersion() {
        return TokenTypes.LITHIUM_VERSION;
    }

    @Override
    void writeQNameInternal(final QName qname) throws IOException {
        defaultWriteQName(qname);
    }

    @Override
    void writeModule(final QNameModule module) throws IOException {
        defaultWriteModule(module);
    }

    @Override
    void writeAugmentationIdentifier(final AugmentationIdentifier aid) throws IOException {
        defaultWriteAugmentationIdentifier(aid);
    }
}
